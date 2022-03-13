package com.rmcgoff.watchthisspaceship.domain.model

data class Launch (
    val missionName: String,
    val launchDate: String,
    val launchTime: String,
    // Days since/from todays date
    val launchDays: Long,
    val rocketName: String,
    val rocketType: String,
    val wasSuccessful: Boolean,
    val patchImage: String,
    val wikipediaLink: String
)