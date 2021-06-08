package com.example.weatherapp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.DataModels.DailyWeatherInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*


class WeatherDetailViewBottomSheetFragment(): BottomSheetDialogFragment() {

    lateinit var fragmentLayout: View
    private val weatherDataViewModel: WeatherDataViewModel by lazy {
        ViewModelProvider(requireActivity()).get(WeatherDataViewModel::class.java)
    }


    override fun onCreateView(
        inflater:LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?):View?{
        fragmentLayout = layoutInflater.inflate(R.layout.daily_weather_detail, container, false)

        weatherDataViewModel.weatherLiveData.observe(
            viewLifecycleOwner,
            { weatherDaysInfo ->
                val dateId: Int = arguments?.getSerializable("DATE") as Int
                for(day in weatherDaysInfo) {
                    if(day.dt == dateId)
                        setDataToDetailView(day)
                    (dialog as? BottomSheetDialog)?.behavior?.apply {
                        setFitToContents(false)
                    }
                }
            },

            )

        return fragmentLayout

    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
            // context = activity
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDataToDetailView(weatherDayInfo: DailyWeatherInfo) {
        timeCheck()
            addImage(weatherDayInfo)
            addTopTemp(weatherDayInfo)
            addDescription(weatherDayInfo)
            addRealFeelTemp(weatherDayInfo)
            addDayAndEveningTemps(weatherDayInfo)
            addMinAndMaxTemp(weatherDayInfo)
            addHumidity(weatherDayInfo)
            addPressure(weatherDayInfo)

            if(weatherDayInfo.temp.day>25 || weatherDayInfo.temp.day < 10) {
                var isHot = false
            if(weatherDayInfo.temp.day>25) {
                isHot = true
            }
            addWarningText(isHot)
        }

    }

    private fun addMinAndMaxTemp(weatherDayInfo: DailyWeatherInfo) {
        val dayTemp = fragmentLayout.findViewById<TextView>(R.id.max_temp)
        dayTemp.text = "Max Temp: "+ (weatherDayInfo.temp.max).toString()

        val nightTemp = fragmentLayout.findViewById<TextView>(R.id.min_temp)
        nightTemp.text = "Min Temp:"+ (weatherDayInfo.temp.min.toString())
    }


    private fun timeCheck() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    }

    private fun addTopTemp(weatherDayInfo: DailyWeatherInfo) {
        val topTemp = fragmentLayout.findViewById<TextView>(R.id.current_temp)
        topTemp?.text = weatherDayInfo.temp.day.toString() + "°"
    }

    private fun addHumidity(weatherDayInfo: DailyWeatherInfo) {
        val nightTemp = fragmentLayout.findViewById<TextView>(R.id.humidity)
        nightTemp.text = "Humidity (%) :"+ (weatherDayInfo.humidity.toString())
    }

    private fun addPressure(weatherDayInfo: DailyWeatherInfo) {
        val dayTemp = fragmentLayout.findViewById<TextView>(R.id.pressure)
        dayTemp.text = "Pressure (at Sea Leve, hPa): "+ (weatherDayInfo.pressure).toString()

    }

    private fun addDayAndEveningTemps(weatherDayInfo: DailyWeatherInfo) {
        val dayTemp = fragmentLayout.findViewById<TextView>(R.id.day_temp)
        dayTemp.setTextColor(resources.getColor(R.color.yellow, null))
        dayTemp.text = "Day Temp: "+ (weatherDayInfo.temp.day).toString()

        val nightTemp = fragmentLayout.findViewById<TextView>(R.id.night_temp)
        nightTemp.setTextColor(resources.getColor(R.color.colorPrimaryDark, null))
        nightTemp.text = "Night Temp:"+ (weatherDayInfo.temp.night.toString())
    }

    private fun addWarningText(isHot:Boolean) {
        val warningText = fragmentLayout.findViewById<TextView>(R.id.temperature_warning)
        if(isHot) {
            warningText.visibility = View.VISIBLE
            warningText.text = "It is hot!"
            warningText.setTextColor(resources.getColor(R.color.red, null))
        } else {
            warningText.visibility = View.VISIBLE
            warningText.text = "It is cold!"
        }
    }

    private fun addRealFeelTemp(weatherDayInfo: DailyWeatherInfo) {
        val realFeel = fragmentLayout.findViewById<TextView>(R.id.real_feel_description)
        realFeel.text = "Feels Like " + weatherDayInfo.feels_like.day.toString() + "°"
    }

    private fun addDescription(weatherDayInfo: DailyWeatherInfo) {
        val feelingDescription = fragmentLayout.findViewById<TextView>(R.id.sky_description_text)
        var d =  weatherDayInfo.weather[0].description
        feelingDescription.text = d
    }

    private fun addImage(weatherDayInfo: DailyWeatherInfo) {
        val skyDescriptionIcon = weatherDayInfo.weather[0].icon
        val skyView = fragmentLayout.findViewById<ImageView>(R.id.sky_icon)
        val picassoBuilder = Picasso.Builder(context)
        val picassoInstance = picassoBuilder.build()
        val imgUrl = "https://openweathermap.org/img/wn/$skyDescriptionIcon@2x.png"
        picassoInstance
            .load(imgUrl)
            .resize(350,350)
            .centerCrop()
            .into(skyView)
    }

    companion object {

        fun newInstance(day: Int?): WeatherDetailViewBottomSheetFragment {
           val args = Bundle().apply {
               putSerializable("DATE", day)
           }

            return WeatherDetailViewBottomSheetFragment().apply{
                arguments = args
            }
        }
    }



    }

