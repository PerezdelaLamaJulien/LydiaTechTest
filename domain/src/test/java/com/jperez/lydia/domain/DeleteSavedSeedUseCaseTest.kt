package com.jperez.lydia.domain

import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.domain.usecase.DeleteSavedSeedUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Unit tests for the DeleteSavedSeedUseCase class.
 */
class DeleteSavedSeedUseCaseTest : KoinTest {
    private lateinit var mockRepository: ContactRepository
    private lateinit var deleteSavedSeedUseCase: DeleteSavedSeedUseCase

    @Before
    fun setUp() {
        mockRepository = mockk(relaxed = true)
        deleteSavedSeedUseCase = DeleteSavedSeedUseCase()
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
    fun `delete saved seed`() = runTest {
        coEvery { mockRepository.deleteSavedSeed("seed") } answers {}
        deleteSavedSeedUseCase.execute("seed")

        coVerify {
            mockRepository.deleteSavedSeed("seed")
        }
    }
}