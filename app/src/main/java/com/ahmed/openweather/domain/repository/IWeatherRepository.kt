package com.ahmed.openweather.domain.repository

import com.ahmed.openweather.domain.model.Weather

interface IWeatherRepository {

    suspend fun getWeather(latitude: Double, longitude: Double, time : String): Weather
}