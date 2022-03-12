package com.rmcgoff.watchthisspaceship.network.datasource

import com.rmcgoff.watchthisspaceship.network.SpaceXService
import com.rmcgoff.watchthisspaceship.network.model.company.Company
import com.rmcgoff.watchthisspaceship.network.model.launch.Launch
import javax.inject.Inject

class DefaultSpaceXNetworkDataSource @Inject constructor(
    private val spaceXService: SpaceXService
) : SpaceXNetworkDataSource {
    override suspend fun getAllLaunches(): List<Launch> {
        return spaceXService.getAllLaunches().body()!!
    }

    override suspend fun getCompanyInfo(): Company {
        return spaceXService.getCompany().body()!!
    }
}