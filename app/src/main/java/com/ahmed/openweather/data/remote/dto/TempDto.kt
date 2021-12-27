package com.ahmed.openweather.data.remote.dto

import com.ahmed.openweather.domain.model.Temp

data class TempDto(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)

fun TempDto.toTemp(): Temp {

    return Temp(
        max = max.toInt(),
        min = min.toInt()
    )
}