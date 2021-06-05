package com.example.weatherapp

import android.widget.AdapterView
import com.example.weatherapp.DataModels.DailyWeatherInfo

interface itemClickCallback{
    fun onWeatherItemClicked(dwi: DailyWeatherInfo)
}