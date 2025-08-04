package com.jperez.lydia.data

import androidx.paging.testing.asSnapshot
import com.jperez.lydia.data.datasource.ContactLocalDataSource
import com.jperez.lydia.data.datasource.ContactRemoteDataSource
import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.data.repository.ContactRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
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
    private lateinit var mockContactLocalDataSource: ContactLocalDataSource
    private lateinit var repository: ContactRepository

    @Before
    fun setUp() {
        mockContactRemoteDataSource = mockk(relaxed = true)
        mockContactLocalDataSource = mockk(relaxed = true)
        startKoin {
            modules(
                module {
                    single<ContactRemoteDataSource> { mockContactRemoteDataSource }
                    single<ContactLocalDataSource> { mockContactLocalDataSource }
                })
        }
        repository = ContactRepositoryImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get contacts from remote data source`() = runTest {
        coEvery { mockContactLocalDataSource.getRequestedContactFromDatabase("default", page= 1, pageSize = 20) } returns null
        coEvery { mockContactRemoteDataSource.getContacts("default", page= 1, pageSize = 20) } returns DataMockConstants.apiResponseATO

        val result = repository.getContacts("default")

        assertEquals(DataMockConstants.contactATO, result.asSnapshot {  }.first())
    }

    @Test
    fun `getSavedSeeds return list of string`() = runTest {
        coEvery { mockContactLocalDataSource.getsSavedSeeds() } returns listOf("seed1", "seed2")
        val result = repository.getSavedSeeds()
        assertEquals(listOf("seed1", "seed2"), result)
    }

    @Test
    fun `deleteSavedSeed call remote data source`() = runTest {
        coEvery { mockContactLocalDataSource.deleteSavedSeed("seed") } answers {}
        repository.deleteSavedSeed("seed")
        coVerify(exactly = 1) {
            mockContactLocalDataSource.deleteSavedSeed("seed")
        }
    }
}