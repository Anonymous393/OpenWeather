package com.ahmed.openweather.data.remote

import com.ahmed.openweather.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("data/2.5/onecall?exclude=alerts,minutely,hourly&appid=cbd33089f23a3eb52a940b95602ca2c2&units=metric")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("dt") time: String
    ): WeatherDto
}