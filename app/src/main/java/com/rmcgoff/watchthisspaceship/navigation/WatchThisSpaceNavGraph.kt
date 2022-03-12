package com.rmcgoff.watchthisspaceship.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rmcgoff.watchthisspaceship.homeui.HomeScreen
import com.rmcgoff.watchthisspaceship.homeui.HomeViewModel
import com.rmcgoff.watchthisspaceship.homeui.dialog.Filter
import com.rmcgoff.watchthisspaceship.homeui.dialog.FilterDialog
import com.rmcgoff.watchthisspaceship.navigation.MainDestinations.FILTER_DIALOG_ROUTE

/**
 * Destinations used for navigation
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val FILTER_DIALOG_ROUTE = "filterDialogRoute"
}

@Composable
fun WatchThisSpaceNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(
            route = MainDestinations.HOME_ROUTE
        ) {
            HomeScreen(
                homeViewModel = viewModel,
                onFilterButtonClicked = { navController.navigate(FILTER_DIALOG_ROUTE)}
            )
        }
        dialog(FILTER_DIALOG_ROUTE) {
            val currentFilter = viewModel.state.value?.launchesViewState?.filter ?: Filter.ASCENDING
            FilterDialog(currentSelectedFilter = currentFilter, {})
        }
    }
}