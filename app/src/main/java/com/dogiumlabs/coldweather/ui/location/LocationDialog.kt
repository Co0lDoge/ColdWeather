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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dogiumlabs.coldweather.R
import com.dogiumlabs.coldweather.data.location.Candidate
import com.dogiumlabs.coldweather.ui.AppViewModelProvider
import com.dogiumlabs.coldweather.ui.theme.ColdWeatherTheme

@Composable
fun LocationDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState: LocationUiState = viewModel.locationUiState

    // When dropDown box is expanded, pressing outside will shrink dropDown box
    // if pressed again dialog will be closed
    Dialog(onDismissRequest = {
        if (!uiState.isDialogExpanded) {
            viewModel.resetDialogState()
            onDismissRequest()
        }
        else viewModel.shrinkDialog()
    }) {
        LocationDialogCard(
            isExpanded = uiState.isDialogExpanded,
            inputText = uiState.dialogText,
            onTextChange = viewModel::changeText,
            onDismissRequest = {
                viewModel.resetDialogState()
                onDismissRequest()
            },
            onCandidateClick = viewModel::selectCity,
            candidates = viewModel.candidatesState,
            modifier = modifier
        )
    }
}

@Composable
fun LocationDialogCard(
    isExpanded: Boolean,
    inputText: String,
    onTextChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onCandidateClick: (String) -> Unit,
    candidates: List<Candidate>,
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
                onCandidateClick = onCandidateClick,
                candidates = candidates
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Button(onClick = { onDismissRequest() }) {
                    Text(text = stringResource(R.string.cancel))
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Button(onClick = { onDismissRequest() }) {
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
    onCandidateClick: (String) -> Unit,
    candidates: List<Candidate>,
    modifier: Modifier = Modifier
) {
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { /* Works fine without it */ },
        modifier = modifier
    ) {
        TextField(
            placeholder = { Text(text = "Select city") },
            value = inputText,
            onValueChange = {
                onTextChange(it)
            },
            readOnly = false,
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { /* Works fine without it */ }
        ) {
            candidates.forEach { candidate ->
                DropdownMenuItem(
                    text = { Text(text = candidate.formattedAddress) },
                    onClick = { onCandidateClick(candidate.name) }
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
            candidates = listOf(),
            onTextChange = {},
            onDismissRequest = {},
            onCandidateClick = { "" },
            isExpanded = false,
            inputText = "",
        )
    }
}