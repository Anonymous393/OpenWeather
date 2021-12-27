package com.ahmed.openweather.data.remote.dto

import com.ahmed.openweather.domain.model.Current
import java.text.SimpleDateFormat
import java.util.*

data class CurrentDto(
    val clouds: Double,
    val dew_point: Double,
    val dt: Int,
    val feels_like: Double,
    val humidity: Double,
    val pressure: Double,
    val sunrise: Double,
    val sunset: Double,
    val temp: Double,
    val uvi: Double,
    val visibility: Double,
    val weather: List<WeatherIconDto>,
    val wind_deg: Double,
    val wind_gust: Double,
    val wind_speed: Double
)

fun CurrentDto.toCurrent(): Current {
    return Current(
        temp = temp,
        date = dt,
        icons = weather
    )
}

fun Int.toDay(): String {

    return try {
        val sdf = SimpleDateFormat("EEE")
        val netDate = Date(this.toLong() * 1000)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}