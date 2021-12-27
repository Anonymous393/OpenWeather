package com.ahmed.openweather.presentation

sealed class Screen(val route: String) {
    object WeatherListScreen : Screen("weather_list_screen")
    object WeatherDetailScreen : Screen("weather_detail_screen")
}
