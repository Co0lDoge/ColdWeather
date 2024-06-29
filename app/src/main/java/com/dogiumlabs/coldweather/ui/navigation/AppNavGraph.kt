package com.dogiumlabs.coldweather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dogiumlabs.coldweather.ui.home.HomeDestination
import com.dogiumlabs.coldweather.ui.home.HomeScreen
import com.dogiumlabs.coldweather.ui.location.LocationDialog

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var isDialogDisplayed by remember { mutableStateOf(false) }

    // Load dialog to switch location when button is pressed
    if (isDialogDisplayed)
        LocationDialog(onDismissRequest = {isDialogDisplayed = !isDialogDisplayed})

    /** Composable that manages screen navigation **/
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(onLocationButtonClick = { isDialogDisplayed = !isDialogDisplayed })
        }
    }
}