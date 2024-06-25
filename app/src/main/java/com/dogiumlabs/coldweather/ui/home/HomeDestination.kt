package com.dogiumlabs.coldweather.ui.home

import androidx.compose.ui.res.stringResource
import com.dogiumlabs.coldweather.R
import com.dogiumlabs.coldweather.ui.navigation.NavigationDestination

object HomeDestination: NavigationDestination {
    override val route: String
        get() = "home_screen"
    override val titleRes: Int
        get() = R.string.home_screen_title //
}