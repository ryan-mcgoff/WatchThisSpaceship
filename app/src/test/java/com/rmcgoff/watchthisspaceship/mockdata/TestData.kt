package com.rmcgoff.watchthisspaceship

import com.rmcgoff.watchthisspaceship.cache.entity.CompanyEntity
import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity

val TEST_LAUNCHES_CACHE = listOf(
    LaunchEntity(
        id = 1,
        missionName = "",
        launchDateUtc = "",
        launchDateUnix = 0L,
        rocketType = "",
        rocketName = "",
        wasSuccessful = true,
        patchImage = "",
        wikipediaLink = ""
    ),
    LaunchEntity(
        id = 2,
        missionName = "",
        launchDateUtc = "",
        launchDateUnix = 0L,
        rocketType = "",
        rocketName = "",
        wasSuccessful = true,
        patchImage = "",
        wikipediaLink = ""
    ),
    LaunchEntity(
        id = 3,
        missionName = "",
        launchDateUtc = "",
        launchDateUnix = 0L,
        rocketType = "",
        rocketName = "",
        wasSuccessful = true,
        patchImage = "",
        wikipediaLink = ""
    )
)

val TEST_COMPANY = CompanyEntity("","","",0L,0L,"")