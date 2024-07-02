package com.dogiumlabs.coldweather.ui.location

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
    val candidates: List<Candidate> = listOf()
)

/** Class that defines logic for LocationDialog  **/
class LocationViewModel(private val locationRepository: LocationRepository) : ViewModel() {
    var locationUiState by mutableStateOf(LocationUiState())
        private set

    private fun updateCandidates(input: String) {
        viewModelScope.launch {
            locationUiState = try {
                LocationUiState(
                    isDialogExpanded = true,
                    dialogText = input,
                    candidates = locationRepository.getLocation(input).candidates
                        // Filter only cities by locality type
                        .filter { candidate ->
                            candidate.types.contains("locality")
                        }
                )
            } catch (e: IOException) {
                LocationUiState(
                    isDialogExpanded = false,
                    dialogText = e.message.toString(),
                    candidates = listOf()
                )
            } catch (e: HttpException) {
                LocationUiState(
                    isDialogExpanded = false,
                    dialogText = e.message.toString(),
                    candidates = listOf()
                )
            }
        }
    }

    fun changeText(input: String) {
        /** Changes text in TextField and updates candidates when typed **/
        locationUiState = locationUiState.copy(
            isDialogExpanded = true,
            dialogText = input
        )

        // Update candidates when typed
        updateCandidates(input)
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