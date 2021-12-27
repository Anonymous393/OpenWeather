package com.ahmed.openweather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmed.openweather.presentation.weather_detail.WeatherDetailScreen
import com.ahmed.openweather.presentation.weather_list.WeatherListScreen
import com.ahmed.openweather.presentation.ui.theme.OpenWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.WeatherListScreen.route
                    ) {
                        composable(
                            route = Screen.WeatherListScreen.route
                        ) {
                            WeatherListScreen(navController)
                        }
                        composable(
                            route = Screen.WeatherDetailScreen.route + "/{weatherDay}"
                        ) {
                            WeatherDetailScreen()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OpenWeatherTheme {

    }
}