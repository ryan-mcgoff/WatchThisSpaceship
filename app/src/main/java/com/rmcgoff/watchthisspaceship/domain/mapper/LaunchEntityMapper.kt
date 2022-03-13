package com.rmcgoff.watchthisspaceship.domain.mapper

import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.network.model.launch.LaunchNetwork
import javax.inject.Inject

/**
 * Maps from Launch network DTO to LaunchEntity
 */
class LaunchEntityMapper @Inject constructor() : Mapper<LaunchNetwork, LaunchEntity> {
    override suspend fun map(from: LaunchNetwork): LaunchEntity {
        return LaunchEntity(
            missionName = from.name,
            launchDateUtc = from.date_utc,
            patchImage = from.links.patch.small ?: "",
            rocketName = from.name,
            rocketType = from.name,
            wasSuccessful = from.success,
            launchDateUnix = from.date_unix,
            wikipediaLink = from.links.wikipedia ?: ""
        )
    }
    override suspend fun mapList(fromList: List<LaunchNetwork>): List<LaunchEntity> {
        return fromList.map { launch ->
            map(launch)
        }
    }
}