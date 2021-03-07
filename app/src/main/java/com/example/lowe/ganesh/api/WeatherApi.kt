package com.example.lowe.ganesh.api

import com.example.lowe.ganesh.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast?")
    suspend fun getForcast(
        @Query("q") cityName: String,
        @Query("appid") appid: String = "65d00499677e59496ca2f318eb68c049"
    ): WeatherResponse
}