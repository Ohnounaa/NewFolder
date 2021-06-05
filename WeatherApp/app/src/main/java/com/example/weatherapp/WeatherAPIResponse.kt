package com.example.weatherapp

import android.graphics.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class WeatherAPIResponse(val list: List<DailyWeatherData>,
) {
    data class DailyWeatherData(var dt:String = "",
                                          var sunrise: String = "",
                                          var sunset: String = "",
                                          var temp: Array<String> = arrayOf(),
                                          var feels_like: Array<String> = arrayOf(),
                                          var pressure: Int = 0,
                                          var humidity: Int = 0,
                                          var weather: Array<String> = arrayOf(),
                                          var speed: Float = 0.0F,
                                          var deg: Int = 0,
                                          var gust: Float = 0.0F,
                                          var clouds: Int = 0,
                                          var pop: Int = 0)



}