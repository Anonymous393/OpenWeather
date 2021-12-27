package com.ahmed.openweather.domain.model

import com.ahmed.openweather.data.remote.dto.WeatherIconDto

data class Current(
    val date: Int,
    val temp: Double,
    val icons: List<WeatherIconDto>,
)
