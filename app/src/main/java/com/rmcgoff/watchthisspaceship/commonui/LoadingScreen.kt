package com.rmcgoff.watchthisspaceship.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    loadedState: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        when (isLoading) {
            true ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            false -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    loadedState()
                }
            }
        }
    }
}