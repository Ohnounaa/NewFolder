package com.example.weatherapp.database

import androidx.room.TypeConverter
import com.example.weatherapp.DataModels.FeelsLike
import com.example.weatherapp.DataModels.Temp
import com.example.weatherapp.DataModels.WeatherResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class WeatherTypeConverters {

    @TypeConverter
    fun fromFeelsLikeToString(feelsLike: FeelsLike): String =  Gson().toJson(feelsLike);

    @TypeConverter
    fun fromStringtoFeelsLike(feelsLikeString:String): FeelsLike = Gson().fromJson(feelsLikeString, FeelsLike::class)

    @TypeConverter
    fun fromTempToString(temp: Temp): String = Gson().toJson(temp);

    @TypeConverter
    fun fromStringToTemp(tempAsString: String): Temp = Gson().fromJson(tempAsString, Temp::class)

    @TypeConverter
    fun fromUUID(uuid: UUID): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun fromWeatherResponseToString(weatherResponse: kotlin.collections.MutableList<WeatherResponse>): String {
        val gson: Gson = Gson()
        val type: Type = object : TypeTokentlin.collections.MutableList<WeatherResponse>() {}.type
        return gson.toJson(weatherResponse, type)
    }

    @TypeConverter
    fun fromStringToWeatherResponseList(weatherResponseAsString: String): MutableList<WeatherResponse> {
        val gson: Gson = Gson()
        val type: Type =
            object : TypeToken<kotlin.collections.MutableList<WeatherResponse?>?>() {}.getType()
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun toUUID(uuid: String): UUID? {
        return UUID.fromString(uuid)
    }
}