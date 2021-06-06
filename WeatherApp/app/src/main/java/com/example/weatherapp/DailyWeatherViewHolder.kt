
package com.example.weatherapp

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DataModels.DailyWeatherInfo
import com.squareup.picasso.Picasso
import java.util.*

class DailyWeatherViewHolder(itemView: View,
                             private val context: Context?,
) : RecyclerView.ViewHolder(itemView) {

    private var date: TextView? = null
    private var maxDailyTempTextView: TextView? = null
    private var nightTempTextView: TextView? = null
    private var realFeelTempTextView: TextView? = null
    private var skyDescriptionView: ImageView? = null
    private val picassoBuilder = Picasso.Builder(context)
    private val picassoInstance = picassoBuilder.build()

    init {
        date = itemView.findViewById(R.id.date)
        maxDailyTempTextView = itemView.findViewById(R.id.avg_day_temp)
        nightTempTextView = itemView.findViewById(R.id.avg_night_temp)
        realFeelTempTextView = itemView.findViewById(R.id.real_feel_temperature)
        skyDescriptionView = itemView.findViewById(R.id.sky_description)
    }

    fun setDate(unixDate: Int) {
        val calendarDate = Date(unixDate.toLong() * 1000)
        date?.text = calendarDate.toString()
    }

    fun setAverageDayTemperature(dayTemp: Double) {
        maxDailyTempTextView?.text = "Day: "+ dayTemp.toString()+"°"
    }

    fun setAverageNightTemperature(nightTemp: Double) {
        nightTempTextView?.text = "Night: " + nightTemp.toString()+"°"
    }

    fun setRealFeelTemp(realFeelTemp: Double) {
        realFeelTempTextView?.text = "Real Feel: "+ realFeelTemp.toString()+"°"
    }

    fun setSkyDescription(skyDescriptionIcon: String) {
        val imgUrl = "https://openweathermap.org/img/wn/$skyDescriptionIcon@2x.png"
        picassoInstance
            .load(imgUrl)
            .resize(250,250)
            .centerCrop()
            .into(skyDescriptionView)
    }

    fun onClick(clickListener: itemClickCallback, position:Int) {
//        itemView.setOnClickListener{
//            clickListener.onWeatherItemClicked(position)
//        }
    }
}