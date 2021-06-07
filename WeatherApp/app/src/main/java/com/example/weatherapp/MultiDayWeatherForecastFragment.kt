package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActionBarContainer
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DataModels.DailyWeatherInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import java.util.*

class MultiDayWeatherForecastFragment: Fragment(),
    AdapterView.OnItemSelectedListener {

    interface Callbacks{
        fun onDaySelected(weatherDt:Int?)
    }


    private var callbacks: Callbacks? = null

    lateinit var recyclerView: RecyclerView

    private val weatherDataViewModel:WeatherDataViewModel by lazy {
        ViewModelProvider(this).get(WeatherDataViewModel::class.java)
    }

    override fun onAttach(context:Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        val v = inflater.inflate(R.layout.multi_day_weather, container, false)

        recyclerView = v.findViewById(R.id.daily_weather_data_collection)
        recyclerView.layoutManager = LinearLayoutManager(context)
       // setUnitSelectorMenu()

        return v
    }

    override fun onViewCreated(view:View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherDataViewModel.weatherLiveData.observe(
            viewLifecycleOwner,
            { weatherInfo ->
                recyclerView.adapter = DailyWeatherRecyclerViewAdapter(weatherInfo, context, callbacks)
            },
        )
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

//    private fun setUnitSelectorMenu() {
//        val unitsSelector: Spinner = findViewById(R.id.unit_selector)
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.temperature_units_array,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
//            unitsSelector.adapter = adapter
//        }
//        unitsSelector.onItemSelectedListener = this
//    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    companion object{
        fun newInstance(): MultiDayWeatherForecastFragment {
            return MultiDayWeatherForecastFragment()
        }
    }

    private fun convertCelsiusToFahrenheit(celsiusTemp: Double): Double {
        return ("%.2f".format((1.8 * celsiusTemp) + 32)).toDouble();
    }

    private fun convertFahrenheitToCelsius(fahrenheitTemp: Double): Double {
        return ("%.2f".format(((5).toDouble() / 9) * (fahrenheitTemp - 32))).toDouble();
    }
}