package com.rmcgoff.watchthisspaceship.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rmcgoff.watchthisspaceship.commonui.WebViewScreen
import com.rmcgoff.watchthisspaceship.homeui.HomeScreen
import com.rmcgoff.watchthisspaceship.homeui.HomeViewModel
import com.rmcgoff.watchthisspaceship.homeui.dialog.Filter
import com.rmcgoff.watchthisspaceship.homeui.dialog.FilterDialog
import com.rmcgoff.watchthisspaceship.navigation.MainDestinations.ARG_WEBLINK
import com.rmcgoff.watchthisspaceship.navigation.MainDestinations.FILTER_DIALOG_ROUTE
import com.rmcgoff.watchthisspaceship.navigation.MainDestinations.WEB_VIEW_ROUTE
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * Destinations used for navigation
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val WEB_VIEW_ROUTE = "webView"
    const val FILTER_DIALOG_ROUTE = "filterDialogRoute"
    const val ARG_WEBLINK = "webLink"
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

        // Main home screen
        composable(
            route = MainDestinations.HOME_ROUTE
        ) {
            HomeScreen(
                homeViewModel = viewModel,
                onFilterButtonClicked = { navController.navigate(FILTER_DIALOG_ROUTE) },
                onLaunchItemClicked = {link ->
                    val encodedUrl = URLEncoder.encode(link, StandardCharsets.UTF_8.toString())
                    navController.navigate("$WEB_VIEW_ROUTE/$encodedUrl")
                }
            )
        }

        // Webview
        composable(
            route = "${WEB_VIEW_ROUTE}/{$ARG_WEBLINK}",
            arguments = listOf(navArgument(ARG_WEBLINK) { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val decodedUrl = URLDecoder.decode(arguments.getString(ARG_WEBLINK) ?: "", StandardCharsets.UTF_8.toString())
            WebViewScreen(urlToRender = decodedUrl)
        }

        // Filter Dialog
        dialog(FILTER_DIALOG_ROUTE) {
            val currentFilter = viewModel.state.value?.launchesViewState?.filter ?: Filter.ASCENDING
            FilterDialog(currentSelectedFilter = currentFilter) { filterSelected ->
                navController.popBackStack()
                viewModel.filterLaunches(filterSelected)
            }
        }
    }
}