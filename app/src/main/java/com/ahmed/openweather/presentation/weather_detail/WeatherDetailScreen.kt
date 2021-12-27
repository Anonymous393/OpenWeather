package com.ahmed.openweather.presentation.weather_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmed.openweather.common.Constants
import com.ahmed.openweather.domain.model.toDay
import com.ahmed.openweather.domain.model.toDayFull
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun WeatherDetailScreen(viewModel: WeatherDetailViewModel = hiltViewModel()) {
    val dayWeather = viewModel.dayWeather

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
        val (currentDay, currentIcon, currentDescription, currentWeather, divider, maxTemp, minTemp) = createRefs()
        val guideline = createGuidelineFromTop(0.1f)

        Text(
            modifier = Modifier
                .constrainAs(currentDay) {
                    top.linkTo(parent.top)
                    centerHorizontallyTo(parent)
                },
            text = dayWeather.date.toDayFull(),
            color = Color.White,
            fontSize = 48.sp
        )

        GlideImage(
            modifier = Modifier
                .size(160.dp, 160.dp)
                .constrainAs(currentIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(guideline)
                },
            imageModel = String.format(
                Constants.ICONS_URL,
                dayWeather.icons?.first()?.icon
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
                    centerVerticallyTo(currentIcon)
                    start.linkTo(currentIcon.end)
                },
            text = (dayWeather.icons?.first()?.main ?: "") + Constants.DEGREES,
            color = Color.White,
            fontSize = 48.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(maxTemp) {
                    centerHorizontallyTo(parent)
                    bottom.linkTo(divider.top)
                },
            text = "${dayWeather.temp.max}" + Constants.DEGREES + 'C',
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontSize = 56.sp
        )

        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .width(80.dp)
                .height(1.dp)
                .background(Color.White)
                .constrainAs(divider) {
                    centerTo(parent)
                }
        )

        Text(
            modifier = Modifier
                .constrainAs(minTemp) {
                    centerHorizontallyTo(parent)
                    top.linkTo(divider.bottom)
                },
            text = "${dayWeather.temp.min}" + Constants.DEGREES + 'C',
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontSize = 56.sp
        )

    }
}