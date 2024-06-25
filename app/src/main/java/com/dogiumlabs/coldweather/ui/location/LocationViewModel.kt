package com.dogiumlabs.coldweather.ui.location

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


/** Class that allows to hold state of LocationDialog **/
data class LocationUiState(
    val isExpanded: Boolean = false,
    val text: String = ""
)

/** Class that defines logic for LocationDialog  **/
class LocationViewModel: ViewModel() {
    var locationUiState by mutableStateOf(LocationUiState())

    init {
        // TODO: get data from cityAPI
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