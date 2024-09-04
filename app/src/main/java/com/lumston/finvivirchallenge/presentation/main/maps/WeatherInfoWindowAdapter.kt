package com.lumston.finvivirchallenge.presentation.main.maps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.databinding.WeatherInfoLayoutBinding
import com.lumston.finvivirchallenge.framework.extensions.iconToLocalResource

class WeatherInfoWindowAdapter(
    private val context: Context
): GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View {
        val inflater = LayoutInflater.from(context)
        val binding = WeatherInfoLayoutBinding.inflate(inflater)

        if (marker.tag is WeatherInfo) {
            val info = marker.tag as WeatherInfo

            // Title
            binding.title.text = info.name

            // Temperature
            val temperature = "${info.weather?.temp ?: "?"} °C"
            binding.temperatureText.text = temperature

            // Weather condition
            if (info.conditions.isNotEmpty()) {
                val condition = info.conditions[0]
                val description = "${condition.main} - ${condition.description}"
                binding.description.text = description
                binding.icon.setImageResource(condition.iconToLocalResource())
            }
        }

        return binding.root
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }
}