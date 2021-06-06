package com.example.weatherapp.DataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.database.WeatherTypeConverters
import java.util.*

@Entity
data class DailyWeatherInfo (@PrimaryKey val id: UUID = UUID.randomUUID(),
                             val clouds: Int,
                             val deg: Int,
                             val dt: Int,
                             @TypeConverters(WeatherTypeConverters::class)
                             val feels_like: FeelsLike,
                             val gust: Double,
                             val humidity: Int,
                             val pop: Float,
                             val pressure: Int,
                             val rain: Double,
                             val speed: Double,
                             val sunrise: Int,
                             val sunset: Int,
                             @TypeConverters(WeatherTypeConverters::class)
                             val temp: Temp,
                             @TypeConverters(WeatherTypeConverters::class)
                             val weather: List<WeatherResponse>
)