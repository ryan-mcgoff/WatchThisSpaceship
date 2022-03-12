package com.rmcgoff.watchthisspaceship.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rmcgoff.watchthisspaceship.homeui.HomeScreen

/**
 * Destinations used for navigation
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
}

@Composable
fun WatchThisSpaceNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = MainDestinations.HOME_ROUTE
        ) {
            HomeScreen(
                homeViewModel = hiltViewModel()
            )
        }
    }
}