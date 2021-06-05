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
    AdapterView.OnItemSelectedListener,
    itemClickCallback {

    lateinit var weatherDataViewModel: WeatherDataViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //display data in recycler view so we can add many more entries if we want to in the future
        recyclerView = findViewById(R.id.daily_weather_data_collection)
        recyclerView.layoutManager = LinearLayoutManager(this)
        weatherDataViewModel = ViewModelProvider(this).get(WeatherDataViewModel::class.java)
        //as soon as API returns data, add data to adapter
        weatherDataViewModel.weatherLiveData.observe(
            this,
            { weatherInfo ->
                recyclerView.adapter =
                    DailyWeatherRecyclerViewAdapter(weatherInfo, applicationContext, this)
            },
        )
        setUnitSelectorMenu()
    }

    private fun setUnitSelectorMenu() {
        val unitsSelector: Spinner = findViewById(R.id.unit_selector)
        ArrayAdapter.createFromResource(
            this,
            R.array.temperature_units_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            unitsSelector.adapter = adapter
        }
        unitsSelector.onItemSelectedListener = this
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (recyclerView.adapter != null
            && recyclerView.adapter is DailyWeatherRecyclerViewAdapter
            && p1 != null
            && (p1 is AppCompatTextView)
            && (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() != -1
        ) {
            for (weather in (recyclerView.adapter as DailyWeatherRecyclerViewAdapter).weatherLiveData) {
                if (p1.text == "Fahrenheit") {
                    weather.temp.max = convertCelsiusToFahrenheit(weather.temp.max)
                    weather.temp.min = convertCelsiusToFahrenheit(weather.temp.min)
                    weather.feels_like.day = convertCelsiusToFahrenheit(weather.feels_like.day)
                } else if (p1.text == "Celsius") {
                    weather.temp.max = convertFahrenheitToCelsius(weather.temp.max)
                    weather.temp.min = convertFahrenheitToCelsius(weather.temp.min)
                    weather.feels_like.day = convertFahrenheitToCelsius(weather.feels_like.day)
                }
            }
            recyclerView.adapter?.notifyItemRangeChanged(
                0,
                recyclerView.adapter?.itemCount?.minus(1) ?: 0
            )
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    private fun convertCelsiusToFahrenheit(celsiusTemp: Double): Double {
        return ("%.2f".format((1.8 * celsiusTemp) + 32)).toDouble();
    }

    private fun convertFahrenheitToCelsius(fahrenheitTemp: Double): Double {
        return ("%.2f".format(((5).toDouble() / 9) * (fahrenheitTemp - 32))).toDouble();
    }

    override fun onWeatherItemClicked(d: DailyWeatherInfo) {
        supportFragmentManager.let {
            WeatherDetailViewBottomSheetFragment.newInstance(d).apply{
                show(it, "HELLO")
            }
        }
    }
}









