package com.dogiumlabs.coldweather.ui.navigation

/** Interface that describes navigation destination for app's screens**/
interface NavigationDestination {
    val route: String // Value, used for navigation
    val titleRes: Int // Title for display
}