package com.rmcgoff.watchthisspaceship.commonui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Cell(
    modifier: Modifier = Modifier,
    leadingComposable: @Composable (() -> Unit)? = null,
    trailingComposable: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)
) {
    Surface(modifier = modifier) {
        Column {
            Row(Modifier.heightIn(min = 64.dp)) {
                if (leadingComposable != null) {
                    Box(
                        Modifier
                            .align(Alignment.CenterVertically)
                            .padding(
                                start = 16.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            ),
                        contentAlignment = Alignment.CenterStart
                    ) { leadingComposable() }
                }
                Box(
                    Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(
                            start = 32.dp,
                            end = 32.dp
                        )
                ) { content() }
                if (trailingComposable != null) {
                    Box(
                        Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 16.dp)
                    ) { trailingComposable() }
                }
            }
            Divider()
        }
    }
}