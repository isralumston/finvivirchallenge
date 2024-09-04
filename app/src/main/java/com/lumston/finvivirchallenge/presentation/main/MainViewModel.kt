package com.lumston.finvivirchallenge.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.domain.usecases.GetLastPlaceSearchedUC
import com.lumston.finvivirchallenge.domain.usecases.GetWeatherInfoUC
import com.lumston.finvivirchallenge.framework.coroutines.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val getWeatherInfoUC: GetWeatherInfoUC,
    private val getLastPlaceSearchedUC: GetLastPlaceSearchedUC
): ViewModel() {
    var viewContract: MainViewContract? = null

    val weatherInfo: LiveData<WeatherInfo> get() = _weatherInfo
    private val _weatherInfo = MutableLiveData<WeatherInfo>()

    fun loadLastPlaceSearched() {
        viewModelScope.launch(dispatchers.io()) {
            val previousPlace = getLastPlaceSearchedUC()
            previousPlace?.let {
                _weatherInfo.postValue(it)
            }
        }
    }

    fun getWeatherInfo(
        lat: Double, lon: Double
    ) {
        viewContract?.onWeatherInfoRequested()
        viewModelScope.launch(dispatchers.io()) {
            val info = getWeatherInfoUC(lat, lon)
            _weatherInfo.postValue(info)

            withContext(dispatchers.main()) {
                viewContract?.onWeatherInfoReady()
            }
        }
    }
}