package com.example.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DataModels.DailyWeatherInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MainActivity : AppCompatActivity(),
    itemClickCallback {

    lateinit var weatherDataViewModel: WeatherDataViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //display data in recycler view so we can add many more entries if we want to in the future
        //recyclerView = findViewById(R.id.daily_weather_data_collection)
       // recyclerView.layoutManager = LinearLayoutManager(this)

       val visibleFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if(visibleFragment == null) {
            val f = MultiDayWeatherForecastFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, f).commit()
        }

      //  weatherDataViewModel = ViewModelProvider(this).get(WeatherDataViewModel::class.java)
        //as soon as API returns data, add data to adapter
//        weatherDataViewModel.weatherLiveData.observe(
//            this,
//            { weatherInfo ->
//                recyclerView.adapter =
//                    DailyWeatherRecyclerViewAdapter(weatherInfo, applicationContext, this)
//            },
 //       )
        //setUnitSelectorMenu()
    }





    override fun onWeatherItemClicked(d: DailyWeatherInfo) {
        supportFragmentManager.let {
            WeatherDetailViewBottomSheetFragment.newInstance(d).apply{
                show(it, "HELLO")
            }
        }
    }
}









