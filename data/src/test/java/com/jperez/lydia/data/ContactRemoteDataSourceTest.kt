package com.jperez.lydia.data

import com.jperez.lydia.data.api.ApiClient
import com.jperez.lydia.data.datasource.ContactRemoteDataSource
import com.jperez.lydia.data.datasource.ContactRemoteDataSourceImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest


/**
 * Unit tests for the ContactRemoteDataSource class.
 */
class ContactRemoteDataSourceTest : KoinTest {
    private lateinit var mockApiClient: ApiClient
    private lateinit var remoteDataSource: ContactRemoteDataSource

    @Before
    fun setUp() {
        mockApiClient = mockk(relaxed = true)
        remoteDataSource = ContactRemoteDataSourceImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get contacts from api`() = runTest {
        startKoin {
            modules(
                module {
                    single<ApiClient> { mockApiClient }
                })
        }
        coEvery { mockApiClient.getContacts("default", page= 1, pageSize = 10) } returns DataMockConstants.apiResponseATO

        val result = remoteDataSource.getContacts("default",  page= 1, pageSize = 10)

        assertEquals(DataMockConstants.apiResponseATO, result)
    }
}