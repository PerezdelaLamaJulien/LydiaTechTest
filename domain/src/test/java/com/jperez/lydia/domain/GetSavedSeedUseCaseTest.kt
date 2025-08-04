package com.jperez.lydia.domain

import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.domain.usecase.GetSavedSeedUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Unit tests for the GetSavedSeedUseCase class.
 */
class GetSavedSeedUseCaseTest : KoinTest {
    private lateinit var mockRepository: ContactRepository
    private lateinit var getSavedSeedUseCase: GetSavedSeedUseCase

    @Before
    fun setUp() {
        mockRepository = mockk(relaxed = true)
        getSavedSeedUseCase = GetSavedSeedUseCase()
        startKoin {
            modules(
                module {
                    single<ContactRepository> { mockRepository }
                })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get saved seed`() = runTest {
        coEvery { mockRepository.getSavedSeeds() } returns listOf("seed", "seed2")
        val result = getSavedSeedUseCase.execute()
        assertEquals(listOf("seed", "seed2"), result)
    }
}