package com.rmcgoff.watchthisspaceship.commonui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable

@Composable
fun FilterTopBar(
    title: String,
    onFilterButtonClicked: () -> Unit
) {
    TopAppBar(title = { Text(title) }, actions = {
        IconButton(onClick = { onFilterButtonClicked.invoke() }) {
            Icon(
                imageVector = Icons.Filled.List,
                contentDescription = "Filter"
            )
        }
    })
}