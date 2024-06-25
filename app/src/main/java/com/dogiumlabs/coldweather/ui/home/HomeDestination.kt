package com.dogiumlabs.coldweather.ui.home

import com.dogiumlabs.coldweather.ui.navigation.NavigationDestination

object HomeDestination: NavigationDestination {
    override val route: String
        get() = "home_screem"
    override val titleRes: Int
        get() = 4242 // TODO: replace with actual title res

}