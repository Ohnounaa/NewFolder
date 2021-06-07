package com.example.weatherapp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.DataModels.DailyWeatherInfo
import com.example.weatherapp.DataModels.WeatherData
import 	android.util.Log

//retrieve and store data in view model so we have reference to data on configuration changes
class WeatherDataViewModel: ViewModel() {

    val weatherLiveData: MutableLiveData<List<DailyWeatherInfo>> = WeatherDataRetriever().retrieveWeatherData()
    val repo = WeatherDataRepository.retrieve()
    val d: LiveData<List<DailyWeatherInfo>> = repo.getAllWeatherInfo()

    fun testNull() {
        if (d.value == null) {
            Log.d("ALIZA", "NULL")
        } else {
            Log.d("ALIZA", d!!.value!!.size!!.toString())
        }
    }


}