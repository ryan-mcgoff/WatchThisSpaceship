package com.rmcgoff.watchthisspaceship.network.datasource

import com.rmcgoff.watchthisspaceship.network.model.company.Company
import com.rmcgoff.watchthisspaceship.network.model.launch.LaunchNetwork

interface SpaceXNetworkDataSource {
    suspend fun getAllLaunches(): List<LaunchNetwork>
    suspend fun getCompanyInfo(): Company
}