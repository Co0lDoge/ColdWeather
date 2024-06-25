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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.dogiumlabs.coldweather.ui.theme.ColdWeatherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    /** Dialog used to select city **/
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var text by remember {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = { onDismissRequest() }) {
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
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = !isExpanded }
                ) {
                    TextField(
                        value = text,
                        onValueChange = {
                            text = it
                            isExpanded = true
                        },
                        readOnly = false,
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false }
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
}

@Composable
@Preview
fun LocationDialogPreview() {
    ColdWeatherTheme {
        LocationDialog(onDismissRequest = {})
    }
}