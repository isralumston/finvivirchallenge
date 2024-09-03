package com.lumston.finvivirchallenge.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.lumston.finvivirchallenge.R
import com.lumston.finvivirchallenge.databinding.ActivityMainBinding
import com.lumston.finvivirchallenge.presentation.components.WeatherInfoWindowAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    // Google map
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupMapCall()
        setupPlacesApi()
    }

    private fun setupMapCall() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map)
                as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    private fun setupPlacesApi() {
        // Getting the places API
        val autocompletePlacesFragment = supportFragmentManager
            .findFragmentById(R.id.autocomplete_fragment)
                as? AutocompleteSupportFragment

        // Place fields to handle
        autocompletePlacesFragment?.setPlaceFields(
            listOf(
                Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG
            )
        )

        // Place selection listener
        autocompletePlacesFragment?.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                place.latLng?.let {
                    moveMapTo(it)
                }
            }

            override fun onError(status: Status) {}
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        val sydney = LatLng(-33.852, 151.211)
        googleMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
    }

    private fun moveMapTo(latLng: LatLng) {
        val marker = googleMap?.addMarker(MarkerOptions().position(latLng))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.zoomTo(10f))
        googleMap?.setInfoWindowAdapter(WeatherInfoWindowAdapter(this))
        marker?.showInfoWindow()
    }
}