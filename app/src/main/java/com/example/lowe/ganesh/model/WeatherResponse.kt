package com.example.lowe.ganesh.model

import com.example.lowe.ganesh.ui.WeatherItem

data class WeatherResponse(
    val cod: Int,
    val message: Int,
    val cnt: Int,
    val list: List<ListItem>,
    val city: City
)

data class City(
    val id: Int,
    val name: String,
    val country: String,
    val population: Int,
    val timeZone: Int,
    val sunrise: Long,
    val sunset: Long,
    val coord: Coord
)

data class Coord(
    val lat: Float,
    val lon: Float
)

data class ListItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val sys: Sys,
    val dt_txt: String
)

data class Main(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Float,
    val sea_level: Float,
    val grnd_level: Float,
    val humidity: Float,
    val temp_kf: Float
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Clouds(
    val all: Int
)

data class Wind(
    val speed: Float,
    val deg: Int
)
data class Sys(
    val pod: String
)


fun WeatherResponse.getWeatherItemList(): List<WeatherItem> {
    val city = this.city.name
    return this.list.map {
        WeatherItem(
            it.weather.first().main,
            it.main.temp,
            it.main.feels_like,
            it.weather.first().description,
            city
        )
    }
}
