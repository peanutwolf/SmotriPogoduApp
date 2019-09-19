package com.vigurskiy.smotripogoduapp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.vigurskiy.smotripogoduapp.R
import com.vigurskiy.smotripogoduapp.model.DayForecast
import com.vigurskiy.smotripogoduapp.model.ThreeHourForecast
import com.vigurskiy.smotripogoduapp.util.toCelcius
import kotlinx.android.synthetic.main.day_forecast_fragment.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class DayForecastFragment: BaseFragment(){

    private lateinit var dayForecast: DayForecast

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.day_forecast_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dayForecast = (activity as? CanShowDayForecastDetails)?.selectedDayForecast
            ?: throw IllegalStateException("Parent activity must have dayForecast data")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sb_time.min = 0
        }
        sb_time.max = dayForecast.threeHourList.size-1
        sb_time.setOnSeekBarChangeListener(SeekBarChangedListenerImpl())

        showForecast(dayForecast.threeHourList.first())
    }

    private fun showForecast(forecast: ThreeHourForecast){
        txt_date.text = timeFormat.format(forecast.dateTime)

        txt_temp.text = forecast.temp.toCelcius()

        txt_wind.text = getString(R.string.wind, forecast.windSpeed?.toString())

        when(forecast.iconId){
            ThreeHourForecast.ICON_CLEAR_SKY -> R.drawable.clear_sky
            ThreeHourForecast.ICON_FEW_CLOUDS -> R.drawable.few_clouds
            ThreeHourForecast.ICON_SCATTED_CLOUDS -> R.drawable.scattered_clouds
            ThreeHourForecast.ICON_BROKEN_CLOUDS -> R.drawable.broken_clouds
            ThreeHourForecast.ICON_SHOWER_RAIN -> R.drawable.shower_rain
            ThreeHourForecast.ICON_RAIN -> R.drawable.rain
            ThreeHourForecast.ICON_THUNDERSTORM -> R.drawable.thunderstorm
            ThreeHourForecast.ICON_SNOW -> R.drawable.snow
            ThreeHourForecast.ICON_MIST -> R.drawable.mist
            else -> R.drawable.clear_sky
        }.also {
            img_icon.setImageDrawable(context?.getDrawable(it))
        }

        when{

            forecast.rain != null -> {
                txt_precipitation.text = getString(R.string.rain, forecast.rain.toString())
            }

            forecast.snow != null -> {
                txt_precipitation.text = getString(R.string.snow, forecast.snow.toString())
            }

            else -> {
                txt_precipitation.visibility = View.GONE
            }
        }

        txt_description.text = forecast.description

    }

    private inner class SeekBarChangedListenerImpl: SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            showForecast(dayForecast.threeHourList[progress])
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    }

    companion object{
        @SuppressLint("SimpleDateFormat")
        private val timeFormat = SimpleDateFormat("hh a")

        fun newInstance(): DayForecastFragment = DayForecastFragment()

    }
}