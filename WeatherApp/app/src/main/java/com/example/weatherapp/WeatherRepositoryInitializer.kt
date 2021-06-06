package com.example.weatherapp

import android.app.Application
import android.content.res.Configuration

class WeatherRepositoryInitializer: Application() {
    override fun onCreate() {
        super.onCreate()
        WeatherDataRepository.initialize(this)
    }
}
