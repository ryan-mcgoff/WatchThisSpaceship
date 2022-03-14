package com.rmcgoff.watchthisspaceship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.rmcgoff.watchthisspaceship.cache.database.SpaceXDataBase
import com.rmcgoff.watchthisspaceship.navigation.WatchThisSpaceNavGraph
import com.rmcgoff.watchthisspaceship.network.datasource.SpaceXNetworkDataSource
import com.rmcgoff.watchthisspaceship.ui.theme.WatchThisSpaceshipTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var dataSource: SpaceXNetworkDataSource

    @Inject
    lateinit var spaceXDataBase: SpaceXDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchThisSpaceshipTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    WatchThisSpaceNavGraph()
                }
            }
        }
    }
}