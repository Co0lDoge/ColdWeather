package com.dogiumlabs.coldweather.ui.location

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogiumlabs.coldweather.data.location.Location
import com.dogiumlabs.coldweather.network.location.LocationRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

/** Class that allows to hold state of LocationDialog **/
data class LocationUiState(
    val isDialogExpanded: Boolean = false,
    val dialogText: String = "",
)

/** Class that defines logic for LocationDialog  **/
class LocationViewModel(private val locationRepository: LocationRepository) : ViewModel() {
    // State of UI values
    var dialogUiState by mutableStateOf(LocationUiState())
        private set

    // State of list of locations
    var locationsState by mutableStateOf(listOf<Location>())
        private set

    private fun getLocationList(input: String) {
        /** Gets list of place's locations from API **/
        viewModelScope.launch {
            try {
                locationsState = locationRepository.getLocation(input).locations
                    // Filter only cities by locality type
                    .filter { location ->
                        location.type.contains("city")
                    }
                Log.d("LocationDebug", "locations fetched: $locationsState")
            } catch (e: IOException) {
                locationsState = listOf()
                Log.e("LocationDebug", e.message.toString())
            } catch (e: HttpException) {
                listOf("LocationDebug")
                Log.e("LocationDebug", e.message())
            }
        }

    }

    fun changeText(input: String) {
        /** Changes text in TextField and updates locations when typed **/
        dialogUiState = dialogUiState.copy(
            isDialogExpanded = true,
            dialogText = input,
        )

        getLocationList(input)
    }

    fun selectCity(cityName: String) {
        /** Copies the location's name when it's pressed in the drop-down menu **/
        dialogUiState = dialogUiState.copy(
            isDialogExpanded = false,
            dialogText = cityName,
        )
    }

    fun shrinkDialog() {
        /** Shrinks dropdown menu **/
        dialogUiState = dialogUiState.copy(
            isDialogExpanded = false,
        )
    }

    fun updateCity(text: String) {
        // TODO: Change selected city when DropDownItem is clicked
    }

    fun resetDialogState() {
        /** Resets state of LocationDialog when dialog is closed **/
        dialogUiState = dialogUiState.copy(
            isDialogExpanded = false,
            dialogText = "",
        )
    }
}