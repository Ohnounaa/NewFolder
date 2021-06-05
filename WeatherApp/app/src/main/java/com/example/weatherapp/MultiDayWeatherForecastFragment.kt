package com.example.weatherapp

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

class MultiDayWeatherForecastFragment: Fragment() {

    lateinit var recyclerView: RecyclerView

    private val weatherDataViewModel:WeatherDataViewModel by lazy {
        ViewModelProvider(this).get(WeatherDataViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        val v = inflater.inflate(R.layout.multi_day_weather, container, false)
        recyclerView = v.findViewById(R.id.daily_weather_data_collection)
        recyclerView.layoutManager = LinearLayoutManager(context)

        return v
    }

    companion object{
        fun newInstance(): MultiDayWeatherForecastFragment {
            return MultiDayWeatherForecastFragment()
        }

    }
}