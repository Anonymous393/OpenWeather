package com.ahmed.openweather.data.remote.dto

data class WeatherIconDto(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)