package com.example.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DataModels.DailyWeatherInfo


class DailyWeatherRecyclerViewAdapter(
    val weatherLiveData: List<DailyWeatherInfo>,
    private val context: Context?,
    val callbacks: MultiDayWeatherForecastFragment.Callbacks?
) : RecyclerView.Adapter<DailyWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.daily_weather_data_viewholder, parent, false)
        return DailyWeatherViewHolder(view, context, callbacks)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        val weatherDay = weatherLiveData[position]
        holder.bindData(weatherDay)
    }

    override fun getItemCount(): Int {
        return weatherLiveData.size
    }
}