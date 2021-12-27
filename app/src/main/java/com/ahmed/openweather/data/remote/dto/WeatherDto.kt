package com.ahmed.openweather.data.remote.dto

import com.ahmed.openweather.domain.model.Weather

data class WeatherDto(
    val daily: List<DailyDto>,
    val current: CurrentDto,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)

fun WeatherDto.toWeather(): Weather {

    return Weather(
        timezone = timezone,
        daily = daily.map { dailyDto -> dailyDto.toDaily() },
        current = current.toCurrent()
    )
}