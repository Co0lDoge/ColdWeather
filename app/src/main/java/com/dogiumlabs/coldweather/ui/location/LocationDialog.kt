package com.dogiumlabs.coldweather.ui.location

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dogiumlabs.coldweather.R
import com.dogiumlabs.coldweather.data.location.Location
import com.dogiumlabs.coldweather.ui.AppViewModelProvider
import com.dogiumlabs.coldweather.ui.theme.ColdWeatherTheme

@Composable
fun LocationDialog(
    onSubmit: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState: LocationUiState = viewModel.dialogUiState

    // When dropDown box is expanded, pressing outside will shrink dropDown box
    // if pressed again dialog will be closed
    Dialog(onDismissRequest = {
        if (!uiState.isDialogExpanded) {
            viewModel.resetDialogState()
            onDismissRequest()
        } else viewModel.shrinkDialog()
    }) {
        LocationDialogCard(
            isExpanded = uiState.isDialogExpanded,
            inputText = uiState.dialogText,
            onTextChange = viewModel::changeText,
            onShrinkRequest = viewModel::shrinkDialog,
            onDismissRequest = {
                viewModel.resetDialogState()
                onDismissRequest()
            },
            onLocationClick = viewModel::selectCity,
            onSubmitClick = {
                viewModel.updateCity()
                onSubmit()
            },
            locations = viewModel.locationsState,
            modifier = modifier
        )
    }
}

@Composable
fun LocationDialogCard(
    isExpanded: Boolean,
    inputText: String,
    onTextChange: (String) -> Unit,
    onShrinkRequest: () -> Unit,
    onDismissRequest: () -> Unit,
    onLocationClick: (String) -> Unit,
    onSubmitClick: () -> Unit,
    locations: List<Location>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(192.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DialogDropdownBox(
                isExpanded = isExpanded,
                inputText = inputText,
                onTextChange = onTextChange,
                onShrinkRequest = onShrinkRequest,
                onLocationClick = onLocationClick,
                locations = locations
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Button(onClick = onDismissRequest) {
                    Text(text = stringResource(R.string.cancel))
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Button(onClick = {
                    onSubmitClick()
                    onDismissRequest()
                }) {
                    Text(text = stringResource(R.string.submit))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogDropdownBox(
    isExpanded: Boolean,
    inputText: String,
    onTextChange: (String) -> Unit,
    onShrinkRequest: () -> Unit,
    onLocationClick: (String) -> Unit,
    locations: List<Location>,
    modifier: Modifier = Modifier
) {
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { /* Works fine without it */ },
        modifier = modifier
    ) {
        OutlinedTextField(
            placeholder = { Text(text = "Select city") },
            value = inputText,
            onValueChange = {
                onTextChange(it)
            },
            readOnly = false,
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = (isExpanded and locations.isNotEmpty()),
            onDismissRequest = onShrinkRequest
        ) {
            locations.forEach { location ->
                DropdownMenuItem(
                    text = { Text(text = "${location.name}, ${location.countryName}") },
                    onClick = { onLocationClick(location.name) }
                )
            }
        }
    }
}


@Composable
@Preview
fun LocationDialogPreview() {
    ColdWeatherTheme {
        LocationDialogCard(
            locations = listOf(),
            onTextChange = {},
            onShrinkRequest = {},
            onDismissRequest = {},
            onLocationClick = { _ -> /* Do nothing */ },
            onSubmitClick = {},
            isExpanded = false,
            inputText = "",
        )
    }
}