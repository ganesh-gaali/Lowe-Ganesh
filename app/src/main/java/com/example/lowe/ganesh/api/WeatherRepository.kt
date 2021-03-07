package com.example.lowe.ganesh.api

class WeatherRepository(private val apiHelper: ApiHelper) {
    suspend fun getWeatherForcast(cityName: String) = apiHelper.getWebForcast(cityName)
}