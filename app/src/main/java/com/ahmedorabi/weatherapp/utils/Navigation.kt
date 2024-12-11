package com.ahmedorabi.weatherapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmedorabi.weatherapp.features.add_city.ui.AddCityDialog
import com.ahmedorabi.weatherapp.features.add_city.ui.AddCityScreen
import com.ahmedorabi.weatherapp.features.historical.ui.HistoricalScreen
import com.ahmedorabi.weatherapp.features.weather_details.ui.WeatherDetailsScreen


enum class Screen {
    AddCITY,
    ADD_CITY_DIALOG,
    WEATHER_DETAILS,
    HISTORICAL_SCREEN,
}
sealed class NavigationItem(val route: String) {
    object AddCityScreen : NavigationItem(Screen.AddCITY.name)
    object AddCityDialog : NavigationItem(Screen.ADD_CITY_DIALOG.name)
    object WeatherDetailsScreen : NavigationItem(Screen.WEATHER_DETAILS.name)
    object HistoricalScreen : NavigationItem(Screen.HISTORICAL_SCREEN.name)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.AddCityScreen.route,

    ) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(NavigationItem.AddCityScreen.route) {
            AddCityScreen(navController = navController)
        }
        composable(NavigationItem.AddCityDialog.route) {
            AddCityDialog()
        }

        composable(NavigationItem.WeatherDetailsScreen.route + "/{city}") { backStackEntry ->
            val city = backStackEntry.arguments?.getString("city")
            WeatherDetailsScreen(city ?: "")
        }

        composable(NavigationItem.HistoricalScreen.route + "/{city}") { backStackEntry ->
            val city = backStackEntry.arguments?.getString("city")
            HistoricalScreen(city ?: "")
        }
    }
}