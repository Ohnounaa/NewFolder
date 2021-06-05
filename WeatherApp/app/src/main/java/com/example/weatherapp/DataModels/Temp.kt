package com.example.weatherapp.DataModels

data class Temp(
    val day: Double,
    val eve: Double,
    var max: Double,
    var min: Double,
    val morn: Double,
    val night: Double
)