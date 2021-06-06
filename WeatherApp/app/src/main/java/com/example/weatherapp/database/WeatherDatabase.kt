package com.example.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.database.WeatherDao
import com.example.weatherapp.DataModels.DailyWeatherInfo


@Database(entities = [DailyWeatherInfo:: class], version=1, exportSchema = false)
@TypeConverters(WeatherTypeConverters::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun dailyWeatherDao() :WeatherDao
}