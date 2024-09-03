package com.lumston.finvivirchallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumston.finvivirchallenge.domain.usecases.GetWeatherInfoUC
import com.lumston.finvivirchallenge.framework.coroutines.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val getWeatherInfoUC: GetWeatherInfoUC
): ViewModel() {
    fun getWeatherInfo(
        lat: Double, lon: Double
    ) {
        viewModelScope.launch(dispatchers.io()) {
            val info = getWeatherInfoUC(lat, lon)

        }
    }
}