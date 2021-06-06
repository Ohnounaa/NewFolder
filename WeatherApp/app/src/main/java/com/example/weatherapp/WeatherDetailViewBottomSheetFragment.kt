package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.DataModels.DailyWeatherInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WeatherDetailViewBottomSheetFragment(): BottomSheetDialogFragment() {

    lateinit var vm: ViewModel
    lateinit var d: DailyWeatherInfo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm = ViewModelProvider(requireActivity()).get(WeatherDataViewModel::class.java)
        return layoutInflater.inflate(R.layout.daily_weather_detail, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        d = DailyWeatherInfo()
        val dt = arguments?.getSerializable("DATE") as Int
        vm.getDaysWeather(dt);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun addWeatherDataToBottomSheet() {

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

