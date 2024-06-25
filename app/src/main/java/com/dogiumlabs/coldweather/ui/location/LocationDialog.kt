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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dogiumlabs.coldweather.ui.theme.ColdWeatherTheme

@Composable
fun LocationDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel = viewModel(),
) {
    val uiState: LocationUiState = viewModel.locationUiState

    Dialog(onDismissRequest = { onDismissRequest() }) {
        LocationDialogCard(
            locationUiState = uiState,
            onTextChange = viewModel::changeText,
            onDismissRequest = onDismissRequest,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDialogCard(
    locationUiState: LocationUiState,
    onTextChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
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
            Text(text = "Select City")
            ExposedDropdownMenuBox(
                expanded = locationUiState.isExpanded,
                onExpandedChange = { /* TODO */ }
            ) {
                TextField(
                    value = locationUiState.text,
                    onValueChange = {
                        onTextChange(it)
                    },
                    readOnly = false,
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = locationUiState.isExpanded,
                    onDismissRequest = { onDismissRequest() }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "one") },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "two") },
                        onClick = { /*TODO*/ }
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@Composable
@Preview
fun LocationDialogPreview() {
    ColdWeatherTheme {
        LocationDialog(onDismissRequest = {})
    }
}