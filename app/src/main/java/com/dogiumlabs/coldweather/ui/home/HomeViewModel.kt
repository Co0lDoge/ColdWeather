package com.dogiumlabs.coldweather.ui.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogiumlabs.coldweather.data.weather.Weather
import com.dogiumlabs.coldweather.data.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface HomeUiState {
    data class Success(val weather: Weather) : HomeUiState
    data object Error : HomeUiState
    data object Loading : HomeUiState
}

class HomeViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getWeatherState()
    }

    private fun getWeatherState() {
        /** Gets weather from Weather Api retrofit service and updates Ui State**/
        viewModelScope.launch {
            homeUiState = try {
                HomeUiState.Success(weather = weatherRepository.getWeather())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
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

