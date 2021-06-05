package com.example.weatherapp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.DataModels.DailyWeatherInfo
import com.example.weatherapp.DataModels.WeatherData

class WeatherDataViewModel: ViewModel() {
    //store data in view model so we have reference to data on configuration changes
    val weatherLiveData: MutableLiveData<List<DailyWeatherInfo>> = WeatherDataRetriever().retrieveWeatherData()

//    fun getDay(index: Int): DailyWeatherInfo {
//       weatherLiveData(index)
//    }



}