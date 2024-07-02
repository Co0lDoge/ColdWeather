package com.dogiumlabs.coldweather.ui.location

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogiumlabs.coldweather.data.location.Candidate
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
    var locationUiState by mutableStateOf(LocationUiState())
        private set

    // State of list of candidates
    var candidatesState by mutableStateOf(listOf<Candidate>())
        private set

    private fun getCandidates(input: String) {
        viewModelScope.launch {
            candidatesState = try {
                locationRepository.getLocation(input).candidates
                    // Filter only cities by locality type
                    .filter { candidate ->
                        candidate.types.contains("locality")
                    }
            } catch (e: IOException) {
                listOf()
            } catch (e: HttpException) {
                listOf()
            }
        }
        Log.d("LocationDebug", "candidates: ${candidatesState}")
    }

    fun changeText(input: String) {
        /** Changes text in TextField and updates candidates when typed **/
        locationUiState = locationUiState.copy(
            isDialogExpanded = true,
            dialogText = input,
        )

        getCandidates(input)
    }

    fun selectCity(cityName: String) {
        /** Copies the candidate's name when it's pressed in the drop-down menu **/
        locationUiState = locationUiState.copy(
            isDialogExpanded = false,
            dialogText = cityName,
        )
    }

    fun shrinkDialog() {
        locationUiState = locationUiState.copy(
            isDialogExpanded = false,
        )
    }

    fun updateCity(text: String) {
        // TODO: Change selected city when DropDownItem is clicked
    }

    fun resetDialogState() {
        /** Resets state of LocationDialog when dialog is closed **/
        locationUiState = locationUiState.copy(
            isDialogExpanded = false,
            dialogText = "",
        )
    }
}