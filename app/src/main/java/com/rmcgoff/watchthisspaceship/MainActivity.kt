package com.rmcgoff.watchthisspaceship

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rmcgoff.watchthisspaceship.data.database.SpaceXDataBase
import com.rmcgoff.watchthisspaceship.network.datasource.SpaceXNetworkDataSource
import com.rmcgoff.watchthisspaceship.ui.theme.WatchThisSpaceshipTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var dataSource: SpaceXNetworkDataSource

    @Inject
    lateinit var spaceXDataBase: SpaceXDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            val response = dataSource.getAllLaunches()
            Log.d("HELLLLLLLLo", response.toString())
            spaceXDataBase.launchesDao().getAll()
        }
        setContent {
            WatchThisSpaceshipTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WatchThisSpaceshipTheme {
        Greeting("Android")
    }
}