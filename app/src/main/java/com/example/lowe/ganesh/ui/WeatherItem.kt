package com.example.lowe.ganesh.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherItem(
    val label: String,
    val temperature: Float,
    val feelLike: Float,
    val description: String,
    val cityName: String
): Parcelable