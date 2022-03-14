package com.rmcgoff.watchthisspaceship.repositories

import com.google.gson.GsonBuilder
import com.rmcgoff.watchthisspaceship.domain.DefaultSpaceXRepository
import com.rmcgoff.watchthisspaceship.domain.mapper.CompanyEntityMapper
import com.rmcgoff.watchthisspaceship.domain.mapper.LaunchEntityMapper
import com.rmcgoff.watchthisspaceship.mockdata.mockLaunchResponse
import com.rmcgoff.watchthisspaceship.mockdata.mockResponseMissionName
import com.rmcgoff.watchthisspaceship.network.SpaceXService
import com.rmcgoff.watchthisspaceship.network.datasource.DefaultSpaceXNetworkDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class SpaceXRepositoryTest {
    // system in test
    private lateinit var repository: DefaultSpaceXRepository

    // Dependencies
    private val launchEntityMapper = LaunchEntityMapper()
    private val companyEntityMapper = CompanyEntityMapper()
    private val mockLaunchDataBase = MockLaunchDataBase()
    private lateinit var mockNetworkDataSource: DefaultSpaceXNetworkDataSource
    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl
    private lateinit var service: SpaceXService

    @Before
    fun setUp() {
        // Set up mock network
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("launches/")
        service = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(SpaceXService::class.java)
        mockNetworkDataSource = DefaultSpaceXNetworkDataSource(service)
        // Set up mock repo
        repository = DefaultSpaceXRepository(
            companyEntityMapper = companyEntityMapper,
            launchEntityMapper = launchEntityMapper,
            database = mockLaunchDataBase,
            networkDataSource = mockNetworkDataSource
        )
    }

    @Test
    fun launchDataFromNetworkIsInsertedIntoDatabase(): Unit = runBlocking {
        // Add mock response to network
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(mockLaunchResponse)
        )
        repository.getLatestLaunchesAscending()
        // Check that launch data from network is now in database
        assert(repository.getCachedLaunchesAscending()[0].missionName == mockResponseMissionName)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()

    }
}