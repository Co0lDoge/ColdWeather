package com.dogiumlabs.coldweather

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dogiumlabs.coldweather.ui.navigation.AppNavGraph

@Composable
fun ColdWeatherApp(
    navController: NavHostController = rememberNavController()
) {
    /** Top level composable that represents screen of the application **/
    AppNavGraph(navController = navController)
}