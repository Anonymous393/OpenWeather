package com.ahmed.openweather.domain.use_case.get_weather

import com.ahmed.openweather.common.Resource
import com.ahmed.openweather.data.repository.WeatherRepositoryImpl
import com.ahmed.openweather.domain.model.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepositoryImpl
) {
    operator fun invoke(
        latitude: Double,
        longitude: Double,
        time: String
    ): Flow<Resource<Weather>> = flow {
        try {
            emit(Resource.Loading())
            val weather =
                repository.getWeather(latitude = latitude, longitude = longitude, time = time)
            emit(Resource.Success(weather))
        } catch (e: HttpException) {
            emit(Resource.Error<Weather>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Weather>("Couldn't reach server. Check your internet connection."))
        }
    }
}