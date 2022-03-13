package com.rmcgoff.watchthisspaceship.homeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.rmcgoff.watchthisspaceship.R
import com.rmcgoff.watchthisspaceship.commonui.Badge
import com.rmcgoff.watchthisspaceship.commonui.Cell
import com.rmcgoff.watchthisspaceship.commonui.Detail
import com.rmcgoff.watchthisspaceship.domain.model.Launch
import kotlin.math.absoluteValue

@Composable
fun LaunchCell(launch: Launch) {
    Cell(
        leadingComposable = {
            Badge(icon = {
                Image(painter = rememberImagePainter(
                    launch.patchImage,
                    builder = {
                        placeholder(R.drawable.ic_placeholder)
                        crossfade(true)
                        transformations(
                            CircleCropTransformation()
                        )
                    }
                ), contentDescription = "icon")
            })
        },
        trailingComposable = {
            if (launch.wasSuccessful) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_success),
                    contentDescription = "Successful launch"
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_failed),
                    contentDescription = "Failed launch"
                )
            }
        }
    ) {
        Column {
            Detail(title = "Mission Name", caption = launch.missionName)
            Detail(title = "Date/Time", caption = "${launch.launchDate} at ${launch.launchTime}")
            Detail(title = "Rocket", caption = launch.rocketName)
            Detail(
                title = "Days ${
                    if (launch.launchDays < 0) {
                        "since"
                    } else {
                        "from"
                    }
                } now",
                caption = launch.launchDays.absoluteValue.toString()
            )
        }
    }
}