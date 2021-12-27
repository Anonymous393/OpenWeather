package com.ahmed.openweather.domain.model

import com.ahmed.openweather.data.remote.dto.WeatherIconDto
import java.text.SimpleDateFormat
import java.util.*

data class Daily(
    val date: Int,
    val temp: Temp,
    val icons: List<WeatherIconDto>?,
)

fun Int.toDay(): String {

    return try {
        val sdf = SimpleDateFormat("EEE")
        val netDate = Date(this.toLong() * 1000)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}

fun Int.toDayFull(): String {

    return try {
        val sdf = SimpleDateFormat("EEEE")
        val netDate = Date(this.toLong() * 1000)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}