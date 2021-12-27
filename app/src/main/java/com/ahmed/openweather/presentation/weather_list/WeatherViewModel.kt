package com.ahmed.openweather.presentation.weather_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.openweather.common.Resource
import com.ahmed.openweather.domain.use_case.get_weather.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _state = mutableStateOf(WeatherState())
    val state: State<WeatherState> = _state

    init {
        tryGettingWeather()
    }

    private fun getWeather(latitude: Double, longitude: Double) {
        val time = System.currentTimeMillis().toString()
        getWeatherUseCase(
            latitude = latitude,
            longitude = longitude,
            time = time
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        result.data?.let { WeatherState(weather = it) }!!
                }
                is Resource.Error -> {
                    _state.value =
                        WeatherState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = WeatherState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun tryGettingWeather(){
        getWeather(latitude = 50.42289, longitude = 7.09757924)
    }

}