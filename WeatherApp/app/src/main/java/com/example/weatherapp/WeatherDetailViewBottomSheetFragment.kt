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
import java.io.Serializable
import java.util.*

class WeatherDetailViewBottomSheetFragment(): BottomSheetDialogFragment() {

    lateinit var vm: ViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = arguments?.get("INDEX")
            //(vm as WeatherDataViewModel).weatherLiveData(index)
        //(vm as WeatherDataViewModel)
        (vm as WeatherDataViewModel).weatherLiveData

    }

    private fun addWeatherDataToBottomSheet() {

    }

    companion object {
        fun newInstance(day: DailyWeatherInfo): WeatherDetailViewBottomSheetFragment {
            var map: HashMap<String, String> = HashMap<String, String>()
            var v: WeatherDetailViewBottomSheetFragment = WeatherDetailViewBottomSheetFragment()
            var b: Bundle = Bundle()
            b.putSerializable("INDEX", map)
            v.arguments = b
            return v
        }

    }



    }

