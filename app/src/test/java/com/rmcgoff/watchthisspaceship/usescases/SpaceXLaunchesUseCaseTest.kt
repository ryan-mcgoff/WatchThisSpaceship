package com.rmcgoff.watchthisspaceship.usescases

import com.rmcgoff.watchthisspaceship.TEST_LAUNCHES_CACHE
import com.rmcgoff.watchthisspaceship.domain.DataResult
import com.rmcgoff.watchthisspaceship.domain.model.Launch
import com.rmcgoff.watchthisspaceship.domain.usescases.DefaultSpaceXLaunchesUseCase
import com.rmcgoff.watchthisspaceship.domain.usescases.SpaceXLaunchesUseCase
import com.rmcgoff.watchthisspaceship.homeui.dialog.Filter
import com.rmcgoff.watchthisspaceship.mockdata.MockLaunchMapper
import com.rmcgoff.watchthisspaceship.mockdata.MockSpaceXRepoNoNetworkConnection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SpaceXLaunchesUseCaseTest {

    // system in test
    private lateinit var spaceXLaunchesUseCase: SpaceXLaunchesUseCase

    // dependencies
    private val launchMapper = MockLaunchMapper()
    private val repository = MockSpaceXRepoNoNetworkConnection()

    @Before
    fun setUp(){
        spaceXLaunchesUseCase = DefaultSpaceXLaunchesUseCase(
            repository = repository,
            launchMapper = launchMapper
        )
    }

    @Test
    fun whenNetworkError_RetrieveLaunchesFromCache() : Unit = runBlocking {
        val flowItems = spaceXLaunchesUseCase.doWork(Filter.ASCENDING).toList()
        // first emission should be `loading`
        assert(flowItems[0] is DataResult.Loading)
        // second emission should emit the results from cache
        assert(flowItems[1] is DataResult.Success)
        (flowItems[1] as DataResult.Success<List<Launch>>).data.forEachIndexed { index, launch ->
            // Emitted results should match cache
            assert(launch.missionName == TEST_LAUNCHES_CACHE[index].missionName)
        }
    }
}