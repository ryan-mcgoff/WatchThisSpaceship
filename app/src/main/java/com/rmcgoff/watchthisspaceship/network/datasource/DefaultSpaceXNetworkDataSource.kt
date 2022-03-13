package com.rmcgoff.watchthisspaceship.network.datasource

import com.rmcgoff.watchthisspaceship.network.SpaceXService
import com.rmcgoff.watchthisspaceship.network.model.company.Company
import com.rmcgoff.watchthisspaceship.network.model.launch.LaunchNetwork
import javax.inject.Inject

class DefaultSpaceXNetworkDataSource @Inject constructor(
    private val spaceXService: SpaceXService
) : SpaceXNetworkDataSource {
    override suspend fun getAllLaunches(): List<LaunchNetwork> {
        return spaceXService.getAllLaunches().body()!!
    }

    override suspend fun getCompanyInfo(): Company {
        return spaceXService.getCompany().body()!!
    }
}