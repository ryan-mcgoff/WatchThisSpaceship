package com.rmcgoff.watchthisspaceship.commonui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun Detail(title: String, caption: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "$title: ",
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = caption,
            overflow = TextOverflow.Ellipsis
        )
    }
}