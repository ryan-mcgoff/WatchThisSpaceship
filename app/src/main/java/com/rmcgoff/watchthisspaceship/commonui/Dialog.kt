package com.rmcgoff.watchthisspaceship.commonui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun CustomDialog(
    title: String,
    content: @Composable (() -> Unit)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .background(
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = title, modifier = Modifier.padding(16.dp)
        )
        Box(
            modifier = Modifier
                .heightIn(max = LocalConfiguration.current.screenHeightDp.dp * 0.60f)
        ) {
            content()
        }
    }
}