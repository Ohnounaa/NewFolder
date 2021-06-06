package com.example.weatherapp.database

import androidx.room.TypeConverter
import com.example.weatherapp.DataModels.FeelsLike
import com.example.weatherapp.DataModels.Temp
import java.lang.StringBuilder
import java.util.*

class WeatherTypeConverters {

@TypeConverter
    fun fromTemperature(temp: Temp): String {
       return temp.toString()
    }

    fun toTemperature() {

    }

    @TypeConverter
    fun fromUUID(uuid: UUID): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String): UUID? {
        return UUID.fromString(uuid)
    }
}