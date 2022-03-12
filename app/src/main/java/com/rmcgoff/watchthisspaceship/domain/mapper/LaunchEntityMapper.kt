package com.rmcgoff.watchthisspaceship.domain.mapper

import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.network.model.launch.Launch
import javax.inject.Inject

/**
 * Maps from Launch network DTO to LaunchEntity
 */
class LaunchEntityMapper @Inject constructor() : Mapper<Launch, LaunchEntity> {
    override suspend fun map(from: Launch): LaunchEntity {
        return LaunchEntity(
            missionName = from.name,
            launchDate = from.date_unix,
            patchImage = from.links.patch.small ?: "",
            rocketName = from.name,
            rocketType = from.name,
            wasSuccessful = from.success
        )
    }
    override suspend fun mapList(fromList: List<Launch>): List<LaunchEntity> {
        return fromList.map { launch ->
            map(launch)
        }
    }
}