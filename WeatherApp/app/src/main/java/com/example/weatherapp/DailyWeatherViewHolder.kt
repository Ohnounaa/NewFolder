
package com.example.weatherapp

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DataModels.DailyWeatherInfo
import com.squareup.picasso.Picasso
import java.util.*
import java.text.*;

class DailyWeatherViewHolder(itemView: View,
                             private val context: Context?,
                             callback: MultiDayWeatherForecastFragment.Callbacks?
) : RecyclerView.ViewHolder(itemView) {

    lateinit var weatherDay: DailyWeatherInfo
    private var date: TextView? = null
    private var maxDailyTempTextView: TextView? = null
    private var nightTempTextView: TextView? = null
    private var realFeelTempTextView: TextView? = null
    private var skyDescriptionView: ImageView? = null
    private var button: Button =itemView.findViewById(R.id.more_button)
    private val picassoBuilder = Picasso.Builder(context)
    private val picassoInstance = picassoBuilder.build()
    private var cb: MultiDayWeatherForecastFragment.Callbacks? = null

    init {
        date = itemView.findViewById(R.id.date)
        maxDailyTempTextView = itemView.findViewById(R.id.avg_day_temp)
        nightTempTextView = itemView.findViewById(R.id.avg_night_temp)
        realFeelTempTextView = itemView.findViewById(R.id.real_feel_temperature)
        skyDescriptionView = itemView.findViewById(R.id.sky_description_text)
        this.cb = callback
        button.setOnClickListener{
            cb?.onDaySelected(weatherDay.dt)
        }
    }

    fun bindData(dwi: DailyWeatherInfo) {
        weatherDay = dwi
        setDate(dwi.dt)
        setMinTemp(dwi.temp.min)
        setMaxTemp(dwi.temp.max)
        setAvgDayTemp(dwi.temp.day)
        setSkyDescription(dwi.weather[0].icon)


    }

   private fun setDate(unixDate: Int) {
        val calendarDate:Date = Date(unixDate.toLong() * 1000)
        val dateFormatter: SimpleDateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy")
        val d = dateFormatter.format(calendarDate)

       date?.text = d.toString()
    }

   private fun setMinTemp(minTemp: Double) {
        maxDailyTempTextView?.text = "Min Temp: "+ minTemp.toString()+"°"
    }

   private fun setMaxTemp(maxTemp: Double) {
        nightTempTextView?.text = "Max: " + maxTemp.toString()+"°"
    }

    private fun setAvgDayTemp(avgTemp: Double) {
        realFeelTempTextView?.text =  avgTemp.toString()+"°"
    }

    private fun setSkyDescription(skyDescriptionIcon: String) {
        val imgUrl = "https://openweathermap.org/img/wn/$skyDescriptionIcon@2x.png"
        picassoInstance
            .load(imgUrl)
            .resize(250,250)
            .centerCrop()
            .into(skyDescriptionView)
    }
}