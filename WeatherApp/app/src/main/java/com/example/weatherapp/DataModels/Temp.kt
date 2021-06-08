package com.example.weatherapp.DataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class Temp(
    var day: Double,
    var eve: Double,
    var max: Double,
    var min: Double,
    var morn: Double,
    var night: Double
)