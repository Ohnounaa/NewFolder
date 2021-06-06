package com.example.weatherapp.DataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


data class FeelsLike(
                     var day: Double,
                     val eve: Double,
                     val morn: Double,
                     val night: Double
)