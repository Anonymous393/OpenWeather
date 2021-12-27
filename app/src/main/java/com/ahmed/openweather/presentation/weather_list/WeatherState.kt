package com.ahmed.openweather.presentation.weather_list

import com.ahmed.openweather.domain.model.Weather

data class WeatherState(
    val isLoading: Boolean = false,
    val weather: Weather = Weather(emptyList(), "", null),
    val error: String = ""
)
