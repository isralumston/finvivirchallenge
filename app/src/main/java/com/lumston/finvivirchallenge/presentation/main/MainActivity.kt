package com.lumston.finvivirchallenge.presentation.main

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
import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.databinding.ActivityMainBinding
import com.lumston.finvivirchallenge.framework.extensions.gone
import com.lumston.finvivirchallenge.framework.extensions.visible
import com.lumston.finvivirchallenge.presentation.main.maps.WeatherInfoWindowAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback, MainViewContract {
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
        attachObserverAndContract()
    }

    private fun attachObserverAndContract() {
        viewModel.viewContract = this
        viewModel.weatherInfo.observe(this) {
            showWeatherInfoFor(it)
        }
        viewModel.loadLastPlaceSearched()
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
            listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
        )

        // Place selection listener
        autocompletePlacesFragment?.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                place.latLng?.let {
                    viewModel.getWeatherInfo(
                        it.latitude, it.longitude
                    )
                }
            }

            override fun onError(status: Status) {}
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        this.googleMap?.setInfoWindowAdapter(
            WeatherInfoWindowAdapter(this)
        )
    }

    override fun onWeatherInfoRequested() {
        binding.progressIndicator.visible()
    }

    override fun onWeatherInfoReady() {
        binding.progressIndicator.gone()
    }

    private fun showWeatherInfoFor(info: WeatherInfo) {
        // Clear previous markers
        googleMap?.clear()

        // Setup marker tag with weather info
        val latLng = LatLng(info.lat, info.lon)
        val marker = googleMap?.addMarker(MarkerOptions().position(latLng))
        marker?.tag = info

        // Move camera to specified lat lon
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.zoomTo(10f))

        // Show weather info
        marker?.showInfoWindow()
    }
}