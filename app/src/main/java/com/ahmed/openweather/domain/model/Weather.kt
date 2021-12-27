package com.ahmed.openweather.domain.model

data class Weather(
    val daily: List<Daily>,
    val timezone: String,
    val current: Current?
)
