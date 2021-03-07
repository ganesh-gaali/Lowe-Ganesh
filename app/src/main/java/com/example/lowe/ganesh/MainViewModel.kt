package com.example.lowe.ganesh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.lowe.ganesh.api.WeatherRepository
import com.example.lowe.ganesh.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getCityWeather(cityName: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = weatherRepository.getWeatherForcast(cityName)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}