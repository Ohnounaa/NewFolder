package com.example.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DataModels.DailyWeatherInfo

class DailyWeatherRecyclerViewAdapter(
    val weatherLiveData: List<DailyWeatherInfo>,
    private val context: Context,
    private val clickListener:itemClickCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.daily_weather_data_viewholder, parent, false)
        return DailyWeatherViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is DailyWeatherViewHolder-> {
                holder.setDate(weatherLiveData[position].dt)
                holder.setAverageDayTemperature(weatherLiveData[position].temp.day)
                holder.setAverageNightTemperature(weatherLiveData[position].temp.night)
                holder.setRealFeelTemp(weatherLiveData[position].feels_like.day)
                holder.setSkyDescription(weatherLiveData[position].weather[0].icon)
                holder.onClick(clickListener, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return weatherLiveData.size
    }
}