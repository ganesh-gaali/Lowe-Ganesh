package com.example.lowe.ganesh.api

class ApiHelper(private val weatherApi: WeatherApi)  {

    suspend fun getWebForcast(cityName: String) = weatherApi.getForcast(cityName)
}