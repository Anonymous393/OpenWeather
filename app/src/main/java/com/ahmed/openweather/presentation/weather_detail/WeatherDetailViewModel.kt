package com.ahmed.openweather.presentation.weather_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ahmed.openweather.common.Constants
import com.ahmed.openweather.domain.model.Daily
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    lateinit var dayWeather: Daily

    init {
        savedStateHandle.get<String>(Constants.PARAM_Weather)?.let {
            if (it.isNotEmpty()) {
                val gson = Gson()
                dayWeather = gson.fromJson(it, Daily::class.java)
            }
        }
    }
}