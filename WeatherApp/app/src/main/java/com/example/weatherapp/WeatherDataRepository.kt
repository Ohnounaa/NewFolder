package com.example.weatherapp

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.DataModels.DailyWeatherInfo
import com.example.weatherapp.database.WeatherDatabase
import java.lang.IllegalStateException
import androidx.lifecycle.LiveData
import java.util.*
import androidx.lifecycle.MutableLiveData

class WeatherDataRepository private constructor(context: Context){

    private val database: WeatherDatabase = Room.databaseBuilder(
        context.applicationContext,
        WeatherDatabase::class.java,
        "weather-database").build()

    private val weatherDao = database.dailyWeatherDao()

    fun getAllWeatherInfo():  LiveData<MutableList<DailyWeatherInfo>> = weatherDao.getAllWeatherInfo();
    fun getSingleDayWeatherInfo(dt:Int):LiveData<DailyWeatherInfo> = weatherDao.getSingleDayWeatherInfo(dt);

    companion object{
        private var INSTANCE: WeatherDataRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = WeatherDataRepository(context)
            }
        }

        fun retrieve(): WeatherDataRepository {
            return INSTANCE?: throw IllegalStateException("Repository has not been initialized.")
        }

    }
}