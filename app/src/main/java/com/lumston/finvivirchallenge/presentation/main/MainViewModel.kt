package com.lumston.finvivirchallenge.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumston.finvivirchallenge.domain.usecases.GetWeatherInfoUC
import com.lumston.finvivirchallenge.framework.coroutines.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val getWeatherInfoUC: GetWeatherInfoUC
): ViewModel() {
    var viewContract: MainViewContract? = null

    fun getWeatherInfo(
        lat: Double, lon: Double
    ) {
        viewContract?.onWeatherInfoRequested()
        viewModelScope.launch(dispatchers.io()) {
            val info = getWeatherInfoUC(lat, lon)

            withContext(dispatchers.main()) {
                viewContract?.onWeatherInfoReady(info, lat, lon)
            }
        }
    }
}