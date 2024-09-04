package com.lumston.finvivirchallenge.presentation.main.maps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.databinding.WeatherInfoLayoutBinding

class WeatherInfoWindowAdapter(
    private val context: Context
): GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View {
        val inflater = LayoutInflater.from(context)
        val binding = WeatherInfoLayoutBinding.inflate(inflater)

        if (marker.tag is WeatherInfo) {
            val info = marker.tag as WeatherInfo

            // Title
            binding.title.text = info.place

            // Temperature
            val temperature = "${info.temp} °C"
            binding.temperatureText.text = temperature

            // Weather details
            val description = "${info.weather} - ${info.weatherDescription}"
            binding.description.text = description
            binding.icon.setImageResource(info.icon)
        }

        return binding.root
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }
}