package com.example.weatherapp.database

import androidx.room.TypeConverter
import com.example.weatherapp.DataModels.FeelsLike
import com.example.weatherapp.DataModels.Temp
import java.lang.StringBuilder
import java.util.*

class WeatherTypeConverters {

    @TypeConverter
    fun fromFeelsLike(feelsLike: FeelsLike): String {

    }
    @TypeConverter
    fun toFeelsLike() {

    }

    @TypeConverter
    fun fromTemp(temp: Temp): String {
        val sb: StringBuilder = StringBuilder()
        sb.append(temp.day.toString() + ",")
        sb.append(temp.min.toString() + ",")
        sb.append(temp.max.toString()+ ",")
        sb.append(temp.night.toString()+ ",")
        sb.append(temp.eve.toString()+ ",")
        sb.append(temp.morn.toString()+ ",")
        return sb.toString()
    }


    @TypeConverter
    fun toTemp(temp: String):Temp? {
       val arr: Array<String> = temp.split(',')
        var temp: Temp? = null


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