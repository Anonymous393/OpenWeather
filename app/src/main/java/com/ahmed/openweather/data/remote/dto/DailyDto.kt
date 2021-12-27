package com.ahmed.openweather.data.remote.dto

import com.ahmed.openweather.domain.model.Daily

data class DailyDto(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: FeelsLikeDto,
    val humidity: Int,
    val moon_phase: Double,
    val moonrise: Int,
    val moonset: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val snow: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: TempDto,
    val uvi: Double,
    val weather: List<WeatherIconDto>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
)

fun DailyDto.toDaily(): Daily {

    return Daily(
        icons = weather,
        date = dt,
        temp = temp.toTemp()
    )
}