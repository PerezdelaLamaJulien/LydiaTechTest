package com.jperez.lydia.feature

import com.jperez.lydia.domain.usecase.GetContactsUseCase
import com.jperez.lydia.feature.mapper.ListContactItemUIMapper
import com.jperez.lydia.feature.model.ListUIState
import com.jperez.lydia.feature.viewmodel.ContactListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ContactListViewModelTest : KoinTest {
    private lateinit var mockUseCase: GetContactsUseCase
    private lateinit var mockUIMapper: ListContactItemUIMapper
    private lateinit var viewModel: ContactListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setUp() {
        mockUseCase = mockk(relaxed = true)
        mockUIMapper = mockk(relaxed = true)
        viewModel = ContactListViewModel()
    }

    @Test
    fun `getContacts when there are result items are not empty`() = runTest(UnconfinedTestDispatcher()) {
        startKoin {
            modules(
                module {
                    single<GetContactsUseCase> { mockUseCase }
                    single<ListContactItemUIMapper> { mockUIMapper }
                })
        }
        coEvery { mockUseCase.getContacts("default") } returns listOf(FeatureMockConstants.contact)
        coEvery { mockUIMapper.mapTo(FeatureMockConstants.contact) } returns FeatureMockConstants.listContactItemUI
        viewModel.getContacts()

        advanceUntilIdle() // Yields to perform the registrations

        val uiState: ListUIState = viewModel.uiState.value
        assertEquals(false, uiState.isLoading)
        assertEquals(FeatureMockConstants.listContactItemUI, uiState.items.first())
        assertEquals(null, uiState.errorMessage)
        stopKoin()
    }

    @Test
    fun `getContacts when there are no result items are empty and errorMessage is set`() = runTest(UnconfinedTestDispatcher()) {
        startKoin {
            modules(
                module {
                    single<GetContactsUseCase> { mockUseCase }
                    single<ListContactItemUIMapper> { mockUIMapper }
                })
        }
        coEvery { mockUseCase.getContacts("default") } returns emptyList()
        viewModel.getContacts()

        advanceUntilIdle() // Yields to perform the registrations

        val uiState: ListUIState = viewModel.uiState.value
        assertEquals(false, uiState.isLoading)
        assertEquals(true, uiState.items.isEmpty())
        assertEquals("No contacts found", uiState.errorMessage)
        stopKoin()
    }
}
