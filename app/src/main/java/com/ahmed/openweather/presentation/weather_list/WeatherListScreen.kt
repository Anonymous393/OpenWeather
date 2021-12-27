package com.ahmed.openweather.presentation.weather_list

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahmed.openweather.common.Constants
import com.ahmed.openweather.presentation.Screen
import com.ahmed.openweather.presentation.weather_list.components.WeatherListItem
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun WeatherListScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(27, 35, 87),
                        Color(137, 100, 160)
                    )
                )
            )
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        val (city, currentIcon, currentDescription, currentWeather, weatherList, errorText, progressIndicator) = createRefs()
        if (!state.isLoading && state.error.isEmpty()) {
            Text(
                modifier = Modifier
                    .constrainAs(city) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    },
                text = "Berlin",
                color = Color.White,
                fontSize = 32.sp
            )


            GlideImage(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .constrainAs(currentIcon) {
                        start.linkTo(currentWeather.start)
                        centerVerticallyTo(currentDescription)
                    },
                imageModel = String.format(
                    Constants.ICONS_URL,
                    state.weather.current?.icons?.first()?.icon
                ),

                // shows a progress indicator when loading an image.
                loading = {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val indicator = createRef()
                        CircularProgressIndicator(
                            modifier = Modifier.constrainAs(indicator) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        )
                    }
                },
                // shows an error text message when request failed.
                failure = {
                    Text(text = "image request failed.")
                }
            )

            Text(
                modifier = Modifier
                    .constrainAs(currentDescription) {
                        bottom.linkTo(currentWeather.top)
                        start.linkTo(currentIcon.end, 4.dp)
                    },
                text = (state.weather.current?.icons?.first()?.main ?: "N/A") + Constants.DEGREES,
                color = Color.White,
                fontSize = 24.sp
            )

            Text(
                modifier = Modifier
                    .constrainAs(currentWeather) {
                        centerTo(parent)
                    },
                text = state.weather.current?.temp?.toInt().toString() + Constants.DEGREES,
                color = Color.White,
                fontSize = 160.sp
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(weatherList) {
                        bottom.linkTo(parent.bottom)
                    },
                verticalAlignment = Alignment.Bottom
            ) {
                items(state.weather.daily) { weatherDay ->
                    WeatherListItem(dayWeather = weatherDay, onItemClick = {
                        val gson = Gson()
                        val json = gson.toJson(weatherDay)
                        navController.navigate(Screen.WeatherDetailScreen.route + "/${json}")
                    }
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Column(modifier = Modifier
                .padding(horizontal = 24.dp)
                .constrainAs(errorText) {
                    centerTo(parent)
                }) {
                Text(
                    text = state.error,
                    color = Color.Red,
                    textAlign = TextAlign.Center

                )

                Button(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .offset(y = 16.dp),
                    onClick = {
                        if (isOnline(context = context)) {
                            viewModel.tryGettingWeather()
                        }
                    },
                ) {
                    Text(text = "Retry")
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.constrainAs(progressIndicator) {
                centerTo(parent)
            })
        }
    }
}

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
    } else {
        return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
    }
    return false
}


