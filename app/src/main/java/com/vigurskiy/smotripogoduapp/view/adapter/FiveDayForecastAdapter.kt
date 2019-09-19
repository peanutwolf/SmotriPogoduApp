package com.vigurskiy.smotripogoduapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vigurskiy.smotripogoduapp.R
import androidx.recyclerview.widget.RecyclerView
import com.vigurskiy.smotripogoduapp.model.DayForecast
import com.vigurskiy.smotripogoduapp.model.FiveDayForecastData
import com.vigurskiy.smotripogoduapp.model.ThreeHourForecast
import com.vigurskiy.smotripogoduapp.util.toCelcius
import java.text.DecimalFormat
import java.text.SimpleDateFormat


class FiveDayForecastAdapter : RecyclerView.Adapter<FiveDayForecastAdapter.ForecastViewHolder>(){

    var onForecastClickListener: OnForecastClickListener? = null

    var forecast: FiveDayForecastData? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.forecast_card_item, parent, false)
        )

    override fun getItemCount(): Int =
        forecast?.dayForecastList?.size ?: 0

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {

        forecast?.dayForecastList?.get(position)?.also { content ->
            holder.bind(content)
        }

    }

    inner class ForecastViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        private val temp = view.findViewById<TextView>(R.id.txt_temp)
        private val date = view.findViewById<TextView>(R.id.txt_date)
        private val icon = view.findViewById<ImageView>(R.id.img_icon)
        private val wind = view.findViewById<TextView>(R.id.txt_wind)
        private val precipitaion = view.findViewById<TextView>(R.id.txt_precipitation)
        private val description = view.findViewById<TextView>(R.id.txt_description)

        fun bind(dayForecast: DayForecast){
            //TODO get average instead of first
            val firstForecast = dayForecast.threeHourList.first()

            temp.text = firstForecast.temp?.toCelcius()

            date.text = dateFormat.format(firstForecast.dateTime)

            wind.text = view.context.getString(R.string.wind, firstForecast.windSpeed?.toString())

            when(firstForecast.iconId){
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
                icon.setImageDrawable(
                    view.context.getDrawable(it)
                )
            }

            when{

                firstForecast.rain != null -> {
                    precipitaion.text = view.context.getString(R.string.rain, firstForecast.rain.toString())
                }

                firstForecast.snow != null -> {
                    precipitaion.text = view.context.getString(R.string.snow, firstForecast.snow.toString())
                }

                else -> {
                    precipitaion.visibility = View.GONE
                }
            }

            description.text = firstForecast.description

            view.setOnClickListener {
                onForecastClickListener?.dayForecastClicked(dayForecast)
            }

        }
    }

    interface OnForecastClickListener{
        fun dayForecastClicked(dayForecast: DayForecast)
    }

    companion object{
        private val dateFormat = SimpleDateFormat("dd/MM")
    }
}