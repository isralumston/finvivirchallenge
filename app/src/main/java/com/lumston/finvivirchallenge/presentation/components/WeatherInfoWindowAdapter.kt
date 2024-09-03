package com.lumston.finvivirchallenge.presentation.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.lumston.finvivirchallenge.databinding.WeatherInfoLayoutBinding

class WeatherInfoWindowAdapter(
    private val context: Context
): GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View {
        val inflater = LayoutInflater.from(context)
        val binding = WeatherInfoLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }
}