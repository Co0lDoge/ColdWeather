package com.dogiumlabs.coldweather.ui.location

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogiumlabs.coldweather.network.location.LocationRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException


/** Class that allows to hold state of LocationDialog **/
data class LocationUiState(
    val isExpanded: Boolean = false,
    val text: String = ""
)

/** Class that defines logic for LocationDialog  **/
class LocationViewModel(private val locationRepository: LocationRepository) : ViewModel() {
    var locationUiState by mutableStateOf(LocationUiState())

    init {
        getCitiesList()
    }

    private fun getCitiesList() {
        /** Gets list of cities from api **/
        viewModelScope.launch {
            locationUiState = try {
                LocationUiState(
                    isExpanded = false,
                    text = locationRepository.getLocation().candidates[0].formattedAddress
                )
            } catch (e: IOException) {
                LocationUiState(
                    isExpanded = true,
                    text = e.message.toString()
                )
            } catch (e: HttpException) {
                LocationUiState(
                    isExpanded = true,
                    text = e.message.toString()
                )
            }
        }
    }

    fun changeText(text: String) {
        /** Changes text in TextField **/
        locationUiState = locationUiState.copy(
            text = text
        )
    }

    fun updateCity(text: String) {
        // TODO: Change selected city when DropDownItem is clicked
    }


}