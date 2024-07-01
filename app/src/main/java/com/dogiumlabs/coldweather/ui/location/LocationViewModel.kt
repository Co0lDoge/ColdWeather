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

    init {
        getCitiesList()
    }

    private fun getCitiesList() {
        /** Gets list of cities from api **/
        viewModelScope.launch {
            locationUiState = try {
                LocationUiState(
                    isDialogExpanded = false,
                    dialogText = "",
                    candidates = locationRepository.getLocation().candidates
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

    fun changeText(text: String) {
        /** Changes text in TextField when typed **/
        locationUiState = locationUiState.copy(
            isDialogExpanded = true,
            dialogText = text,

        )
    }

    fun selectCity(city: String) {
        /** Copies the candidate's name when it's pressed in the drop-down menu **/
        locationUiState = locationUiState.copy(
            isDialogExpanded = false,
            dialogText = city,
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
}