package com.example.lowe.ganesh.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lowe.ganesh.MainViewModel
import com.example.lowe.ganesh.api.ApiHelper
import com.example.lowe.ganesh.api.WeatherRepository

class ViewModelFactory constructor(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(WeatherRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}