package com.example.weatherapp.DataModels

import android.os.Parcel
import android.os.Parcelable
import com.example.weatherapp.DataModels.City
import com.example.weatherapp.DataModels.DailyWeatherInfo


//overall response
data class WeatherData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DailyWeatherInfo>,
    val message: Double
)