package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.weatherapp.DataModels.DailyWeatherInfo
import java.util.*

@Dao
interface WeatherDao{
    @Query("SELECT * FROM DailyWeatherInfo")
    fun getAllWeatherInfo(): LiveData<MutableList<DailyWeatherInfo>>

    @Query("SELECT * FROM DailyWeatherInfo WHERE id=(:id)")
    fun getSingleDayWeatherInfo(id:UUID): LiveData<DailyWeatherInfo>
}