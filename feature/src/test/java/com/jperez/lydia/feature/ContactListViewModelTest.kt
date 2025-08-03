package com.jperez.lydia.feature

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.jperez.lydia.domain.usecase.GetContactsUseCase
import com.jperez.lydia.feature.model.ListUIState
import com.jperez.lydia.feature.viewmodel.ContactListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
    private lateinit var viewModel: ContactListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setUp() {
        mockUseCase = mockk(relaxed = true)
        viewModel = ContactListViewModel()
    }

    @Test
    fun `getContacts when there are result items are not empty`() = runTest(UnconfinedTestDispatcher()) {
        startKoin {
            modules(
                module {
                    single<GetContactsUseCase> { mockUseCase }
                })
        }
        val flow = flowOf(PagingData.from(listOf(FeatureMockConstants.contact)))
        coEvery { mockUseCase.getContacts("default") } returns flow
        viewModel.getContacts()

        advanceUntilIdle() // Yields to perform the registrations

        val uiState: ListUIState = viewModel.uiState.value

        assertEquals(false, uiState.isLoading)
        assertEquals(1, flow.asSnapshot {  }.size)
        stopKoin()
    }
}
