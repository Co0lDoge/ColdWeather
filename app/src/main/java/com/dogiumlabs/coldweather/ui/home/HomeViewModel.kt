package com.dogiumlabs.coldweather.ui.home


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogiumlabs.coldweather.data.location.SavedLocation
import com.dogiumlabs.coldweather.data.location.SavedLocationRepository
import com.dogiumlabs.coldweather.data.weather.Weather
import com.dogiumlabs.coldweather.network.weather.WeatherRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/** Interface that describes three possible states of UiState for HomeScreen **/
sealed interface HomeUiState {
    data class Success(val weather: Weather) : HomeUiState
    data object Error : HomeUiState
    data object Loading : HomeUiState
}

/** Class that defines logic for HomeScreen **/
class HomeViewModel(
    private val weatherRepository: WeatherRepository,
    private val savedLocationRepository: SavedLocationRepository
) : ViewModel() {
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getWeatherState()
    }

    private fun getWeatherState() {
        /** Gets weather from Weather Api retrofit service and updates Ui State**/
        viewModelScope.launch {
            val location = savedLocationRepository.getLocationStream(1).first().name
            savedLocationRepository.insertLocation(SavedLocation(1, "London"))
            try {
                homeUiState = HomeUiState.Success(
                    weather = weatherRepository.getWeather(location)
                )
                Log.d("WeatherDebug", "Weather fetched successfully")
            } catch (e: IOException) {
                homeUiState = HomeUiState.Error
                Log.d("WeatherDebug", e.message.toString())
            } catch (e: HttpException) {
                homeUiState = HomeUiState.Error
                Log.d("WeatherDebug", e.message())
            }
        }
    }

    fun timeFormatter(time: String): String {
        /** Removes all data from time string, leaving only hour data **/
        val regex = Regex("\\d{2}:\\d{2}")
        val matchResult = regex.find(time)?.value

        if (matchResult != null)
            return matchResult
        else
            return "Invalid time format"
    }
}

