package com.rmcgoff.watchthisspaceship.network

import com.rmcgoff.watchthisspaceship.network.data.company.Company
import com.rmcgoff.watchthisspaceship.network.data.launch.Launch
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXService {
    /**
     * Get all launches
     * Docs: https://github.com/r-spacex/SpaceX-API/blob/master/docs/launches/v4/all.md
     */
    @GET("launches")
    suspend fun getAllLaunches(): Response<List<Launch>>

    /**
     * Get all company info
     * Docs: https://github.com/r-spacex/SpaceX-API/blob/master/docs/company/v4/all.md
     */
    @GET("company")
    suspend fun getCompany(): Response<Company>
}