package com.jperez.lydia.data

import androidx.paging.testing.asSnapshot
import com.jperez.lydia.data.datasource.ContactRemoteDataSource
import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.data.repository.ContactRepositoryImpl
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
 * Unit tests for the ContactRepository class.
 */
class ContactRepositoryTest : KoinTest {
    private lateinit var mockContactRemoteDataSource: ContactRemoteDataSource
    private lateinit var repository: ContactRepository

    @Before
    fun setUp() {
        mockContactRemoteDataSource = mockk(relaxed = true)
        repository = ContactRepositoryImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get contacts from remote data source`() = runTest {
        startKoin {
            modules(
                module {
                    single<ContactRemoteDataSource> { mockContactRemoteDataSource }
                })
        }
        coEvery { mockContactRemoteDataSource.getContacts("default", page= 1, pageSize = 20) } returns DataMockConstants.apiResponseATO

        val result = repository.getContacts("default")

        assertEquals(DataMockConstants.contactATO, result.asSnapshot {  }.first())
    }
}