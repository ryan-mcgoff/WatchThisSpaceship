package com.rmcgoff.watchthisspaceship.data.mapper

import com.rmcgoff.watchthisspaceship.data.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.network.data.launch.Launch
import javax.inject.Inject

/**
 * Maps from Launch network DTO to LaunchEntity
 */
class LaunchEntityMapper @Inject constructor() : Mapper<Launch, LaunchEntity> {
    override suspend fun map(from: Launch): LaunchEntity {
        return LaunchEntity(
            name = from.name
        )
    }
    override suspend fun mapList(fromList: List<Launch>): List<LaunchEntity> {
        return fromList.map { launch ->
            map(launch)
        }
    }
}