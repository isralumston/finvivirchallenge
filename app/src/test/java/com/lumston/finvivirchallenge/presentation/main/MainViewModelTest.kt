package com.lumston.finvivirchallenge.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lumston.finvivirchallenge.data.model.WeatherInfo
import com.lumston.finvivirchallenge.domain.usecases.GetLastPlaceSearchedUC
import com.lumston.finvivirchallenge.domain.usecases.GetWeatherInfoUC
import com.lumston.finvivirchallenge.framework.coroutines.MainDispatcherTestWatcher
import com.lumston.finvivirchallenge.framework.coroutines.TestDispatcherImpl
import com.lumston.finvivirchallenge.framework.extensions.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel
    private val testDispatcherImpl = TestDispatcherImpl()

    // Main dispatcher rule for postValue live data
    @get:Rule
    val mainDispatcherRule = MainDispatcherTestWatcher()

    // Task executor rule for lifecycle components
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    // Mocks
    private val viewContract: MainViewContract = mockk(relaxed = true)
    private val getWeatherInfoUC: GetWeatherInfoUC = mockk()
    private val getLastPlaceSearchedUC: GetLastPlaceSearchedUC = mockk()
    private val weatherInfo: WeatherInfo = mockk()
    private val lat = 35.77
    private val lon = 21.2

    @Before
    fun setup() {
        viewModel = MainViewModel(
            testDispatcherImpl,
            getWeatherInfoUC,
            getLastPlaceSearchedUC
        )
        viewModel.viewContract = viewContract
    }

    @Test
    fun `viewModel loadLastPlaceSearched, getLastPlaceSearchedUC called`() = runTest {
        // GIVEN
        coEvery { getLastPlaceSearchedUC() } returns null

        // WHEN
        viewModel.loadLastPlaceSearched()

        // THEN
        coVerify(exactly = 1) { getLastPlaceSearchedUC() }
    }

    @Test
    fun `viewModel loadLastPlaceSearched, getLastPlaceSearchedUC value passed to liveData`() = runTest {
        // GIVEN
        coEvery { getLastPlaceSearchedUC() } returns weatherInfo

        // WHEN
        viewModel.loadLastPlaceSearched()
        val storedInfo = viewModel.weatherInfo.getOrAwaitValue()

        // THEN
        assert(storedInfo == weatherInfo)
    }

    @Test
    fun `viewModel getWeatherInfo, getWeatherInfoUC called`() = runTest {
        // GIVEN
        coEvery { getWeatherInfoUC(lat, lon) } returns weatherInfo

        // WHEN
        viewModel.getWeatherInfo(lat, lon)

        // THEN
        coVerify(exactly = 1) { getWeatherInfoUC(lat, lon) }
    }

    @Test
    fun `viewModel getWeatherInfo, calls view contract methods`() = runTest {
        // GIVEN
        coEvery { getWeatherInfoUC(lat, lon) } returns weatherInfo

        // WHEN
        viewModel.getWeatherInfo(lat, lon)

        // THEN
        coVerify(exactly = 1) { viewContract.onWeatherInfoRequested() }
        coVerify(exactly = 1) { viewContract.onWeatherInfoReady() }
    }

    @Test
    fun `viewModel getWeatherInfo, weather object stored in liveData`() = runTest {
        // GIVEN
        coEvery { getWeatherInfoUC(lat, lon) } returns weatherInfo

        // WHEN
        viewModel.getWeatherInfo(lat, lon)
        val storedInfo = viewModel.weatherInfo.getOrAwaitValue()

        // THEN
        assert(storedInfo == weatherInfo)
    }
}