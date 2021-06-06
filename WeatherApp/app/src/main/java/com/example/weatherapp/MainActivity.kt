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
import java.util.UUID

class MainActivity : AppCompatActivity(),
     MultiDayWeatherForecastFragment.Callbacks {

    lateinit var weatherDataViewModel: WeatherDataViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val summaryFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if(summaryFragment == null) {
            val f = MultiDayWeatherForecastFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, f).commit()
        }
    }


    override fun onDaySelected(dayId: Int?) {
//Main Activity will launch modal bottom sheet fragment to serve as the detail view
    supportFragmentManager.let{
        WeatherDetailViewBottomSheetFragment.newInstance(dayId).apply {
            show(it, "ALIZA")
        }
        }
    }
}









