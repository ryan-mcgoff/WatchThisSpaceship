package com.rmcgoff.watchthisspaceship.network.datasource

import com.rmcgoff.watchthisspaceship.network.data.company.Company
import com.rmcgoff.watchthisspaceship.network.data.launch.Launch

interface SpaceXNetworkDataSource {
    suspend fun getAllLaunches(): List<Launch>
    suspend fun getCompanyInfo(): Company
}