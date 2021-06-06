package com.example.weatherapp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.DataModels.DailyWeatherInfo
import com.example.weatherapp.DataModels.WeatherData

//retrieve and store data in view model so we have reference to data on configuration changes
class WeatherDataViewModel: ViewModel() {

    val weatherLiveData: MutableLiveData<List<DailyWeatherInfo>> = WeatherDataRetriever().retrieveWeatherData()

    val repo = WeatherDataRepository.retrieve()
    val d: LiveData<MutableList<DailyWeatherInfo>> = repo.getAllWeatherInfo()

    val selectedItem


    fun getDaysWeather(dt: Int) {
        val s = "ALIZA"
        //weatherLiveData.ge = dt
    }

}