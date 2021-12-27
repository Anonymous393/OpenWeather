package com.ahmed.openweather.data.repository

import com.ahmed.openweather.data.remote.OpenWeatherApi
import com.ahmed.openweather.data.remote.dto.toWeather
import com.ahmed.openweather.domain.model.Weather
import com.ahmed.openweather.domain.repository.IWeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi
) : IWeatherRepository {
    override suspend fun getWeather(latitude: Double, longitude: Double, time: String): Weather {
        return api.getWeather(latitude = latitude, longitude = longitude, time = time).toWeather()
    }
}