package com.example.weatherapp.DataModels

data class WeatherResponse(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)