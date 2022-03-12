package com.rmcgoff.watchthisspaceship.homeui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rmcgoff.watchthisspaceship.commonui.FilterTopBar
import com.rmcgoff.watchthisspaceship.commonui.LoadingScreen
import com.rmcgoff.watchthisspaceship.homeui.dialog.Filter

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onFilterButtonClicked: () -> Unit
) {
    val viewState by homeViewModel.state.observeAsState(initial = HomeViewState.EMPTY)
    HomeScreenContent(viewState = viewState, onFilterButtonClicked = onFilterButtonClicked)
}

@Composable
fun HomeScreenContent(
    viewState: HomeViewState,
    onFilterButtonClicked: () -> Unit
) {
    Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = {
            FilterTopBar(title = "SpaceX", onFilterButtonClicked = onFilterButtonClicked)
        },
    ) {
        LoadingScreen(isLoading = viewState.launchesViewState.loading) {
            LazyColumn {
                // Company UI
                item {
                    Heading(
                        modifier = Modifier.padding(
                            start = 8.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        ),
                        text = "Company"
                    )
                }
                item {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = viewState.companyInfoViewState.companyInfo
                    )
                }
                // Launches UI
                item {
                    Heading(
                        modifier = Modifier.padding(
                            start = 8.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        ),
                        text = "Launches"
                    )
                    Divider()
                }
                val launches = viewState.launchesViewState.launches
                items(launches.size) { pos ->
                    LaunchCell(launch = launches[pos])
                }
            }
        }
    }
}