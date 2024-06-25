package com.dogiumlabs.coldweather.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColdWeatherTopBar(
    onLocationButtonClick: () -> Unit = { /* TODO */},
    onMenuButtonClick: () -> Unit = { /* TODO */},
) {
    /** Top bar used in multiple screens **/
    CenterAlignedTopAppBar(
        title = {},
        actions = {
            IconButton(onClick = { onMenuButtonClick() }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = null)
            }
            IconButton(onClick = { onLocationButtonClick() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
            }
        }
    )
}