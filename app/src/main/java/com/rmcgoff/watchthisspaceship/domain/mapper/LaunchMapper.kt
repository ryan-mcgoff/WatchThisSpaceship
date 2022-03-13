package com.rmcgoff.watchthisspaceship.domain.mapper

import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.domain.model.Launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import javax.inject.Inject

/**
 * Maps from LaunchEntity to Launch
 */
class LaunchMapper @Inject constructor() : Mapper<LaunchEntity, Launch> {
    override suspend fun map(from: LaunchEntity): Launch {
        val localTimeZone = ZoneId.of(ZoneId.systemDefault().id)
        val launchTimeStamp = Instant.parse(from.launchDateUtc).atZone(localTimeZone)
        val todayTimeStamp  = Instant.now().atZone(localTimeZone)

        return Launch(
            missionName = from.missionName,
            launchDays = ChronoUnit.DAYS.between(todayTimeStamp, launchTimeStamp),
            launchDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(launchTimeStamp),
            patchImage = from.patchImage,
            rocketName = from.rocketName,
            rocketType = from.rocketType,
            wasSuccessful = from.wasSuccessful,
            launchTime = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(launchTimeStamp),
            wikipediaLink = from.wikipediaLink
        )
    }
    override suspend fun mapList(fromList: List<LaunchEntity>): List<Launch> {
        return fromList.map { launch ->
            map(launch)
        }
    }
}