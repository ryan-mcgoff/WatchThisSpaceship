package com.rmcgoff.watchthisspaceship.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit) = {},
    badgeSize: Dp = 64.dp,
) {
    Surface(
        modifier = modifier
            .size(badgeSize),
        shape = RoundedCornerShape(100),
    ) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
        ) {
            icon()
        }
    }
}