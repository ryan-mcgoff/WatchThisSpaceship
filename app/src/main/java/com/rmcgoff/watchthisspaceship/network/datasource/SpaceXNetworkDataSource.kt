package com.rmcgoff.watchthisspaceship.network.datasource

import com.rmcgoff.watchthisspaceship.network.model.company.Company
import com.rmcgoff.watchthisspaceship.network.model.launch.Launch

interface SpaceXNetworkDataSource {
    suspend fun getAllLaunches(): List<Launch>
    suspend fun getCompanyInfo(): Company
}