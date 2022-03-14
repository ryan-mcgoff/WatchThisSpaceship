package com.rmcgoff.watchthisspaceship.mockdata

import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.domain.mapper.Mapper
import com.rmcgoff.watchthisspaceship.domain.model.Launch

class MockLaunchMapper : Mapper<LaunchEntity, Launch> {
    override suspend fun map(from: LaunchEntity): Launch {
        return Launch(from.missionName,"","",0L,"","",true, "","")
    }
}