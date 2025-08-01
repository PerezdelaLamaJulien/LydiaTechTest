package com.jperez.lydia.data

import com.jperez.lydia.data.api.ApiClient
import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.data.repository.ContactRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest


/**
 * Unit tests for the ContactRepository class.
 */
class ContactRepositoryTest : KoinTest {
    private lateinit var mockApiClient: ApiClient
    private lateinit var repository: ContactRepository

    @Before
    fun setUp() {
        mockApiClient = mockk(relaxed = true)
        repository = ContactRepositoryImpl()
    }

    @Test
    fun `get contacts`() = runTest {
        startKoin {
            modules(
                module {
                    single<ApiClient> { mockApiClient }
                })
        }
        coEvery { mockApiClient.getContacts("default") } returns DataMockConstants.apiResponseATO

        val result = repository.getContacts("default")

        assertEquals(true, result.isNotEmpty())
    }
}