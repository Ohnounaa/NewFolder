package com.example.weatherapp.DataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class DailyWeatherInfo (@PrimaryKey val id: UUID = UUID.randomUUID(),
                             val clouds: Int,
                             val deg: Int,
                             val dt: Int,
                             val feels_like: FeelsLike,
                             val gust: Double,
                             val humidity: Int,
                             val pop: Float,
                             val pressure: Int,
                             val rain: Double,
                             val speed: Double,
                             val sunrise: Int,
                             val sunset: Int,
                             val temp: Temp,
                             val weather: List<WeatherResponse>
)