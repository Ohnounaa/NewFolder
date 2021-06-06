package com.example.weatherapp.DataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class Temp(
    val day: Double,
    val eve: Double,
    var max: Double,
    var min: Double,
    val morn: Double,
    val night: Double
)