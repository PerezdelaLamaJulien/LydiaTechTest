package com.jperez.lydia.feature

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.jperez.lydia.domain.usecase.DeleteSavedSeedUseCase
import com.jperez.lydia.domain.usecase.GetContactsUseCase
import com.jperez.lydia.domain.usecase.GetSavedSeedUseCase
import com.jperez.lydia.feature.model.ListUIState
import com.jperez.lydia.feature.viewmodel.ContactListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Unit test for [ContactListViewModel].
 */

@OptIn(ExperimentalCoroutinesApi::class)
class ContactListViewModelTest : KoinTest {
    private lateinit var mockGetContactsUseCase: GetContactsUseCase
    private lateinit var mockGetSavedSeedUseCase: GetSavedSeedUseCase
    private lateinit var mockDeleteSavedSeedUseCase: DeleteSavedSeedUseCase
    private lateinit var viewModel: ContactListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setUp() {
        mockGetContactsUseCase = mockk(relaxed = true)
        mockGetSavedSeedUseCase = mockk(relaxed = true)
        mockDeleteSavedSeedUseCase = mockk(relaxed = true)
        viewModel = ContactListViewModel()
        startKoin {
            modules(
                module {
                    single<GetContactsUseCase> { mockGetContactsUseCase }
                    single<GetSavedSeedUseCase> { mockGetSavedSeedUseCase }
                    single<DeleteSavedSeedUseCase> { mockDeleteSavedSeedUseCase }
                })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getContacts when there are result items are not empty`() =
        runTest(UnconfinedTestDispatcher()) {
            val flow = flowOf(PagingData.from(listOf(FeatureMockConstants.contact)))
            coEvery { mockGetContactsUseCase.execute("default") } returns flow
            viewModel.getContacts()

            advanceUntilIdle() // Yields to perform the registrations

            assertEquals(1, flow.asSnapshot { }.size)
            assertEquals(emptyList<String>(), viewModel.uiState.value.savedSeeds)
            viewModel.getContacts("test")

            advanceUntilIdle() // Yields to perform the registrations
            assertEquals(listOf("test"), viewModel.uiState.value.savedSeeds)
        }

    @Test
    fun `getSavedSeeds call useCase`() =
        runTest(UnconfinedTestDispatcher()) {
            coEvery { mockGetSavedSeedUseCase.execute() } returns listOf("default", "test", "seed")
            viewModel.getSavedSeeds()
            advanceUntilIdle() // Yields to perform the registrations

            val uiState: ListUIState = viewModel.uiState.value
            assertEquals(listOf("default", "test", "seed"), uiState.savedSeeds)
        }

    @Test
    fun `deleteSavedSeed call useCase`() =
        runTest(UnconfinedTestDispatcher()) {
            coEvery { mockGetSavedSeedUseCase.execute() } returns listOf("default", "test", "seed")
            coEvery { mockDeleteSavedSeedUseCase.execute("test") } answers {}

            viewModel.getSavedSeeds()
            advanceUntilIdle() // Yields to perform the registrations

            assertEquals(listOf("default", "test", "seed"), viewModel.uiState.value.savedSeeds)


            viewModel.deleteSavedSeed("test")
            advanceUntilIdle() // Yields to perform the registrations

            assertEquals(listOf("default", "seed"), viewModel.uiState.value.savedSeeds)
        }
}
