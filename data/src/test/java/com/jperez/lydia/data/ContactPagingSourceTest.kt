package com.jperez.lydia.data

import androidx.paging.PagingSource
import com.jperez.lydia.data.datasource.ContactLocalDataSource
import com.jperez.lydia.data.datasource.ContactRemoteDataSource
import com.jperez.lydia.data.paging.ContactPagingSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Unit tests for the ContactPagingSource class.
 */

class ContactPagingSourceTest : KoinTest {
    private lateinit var mockContactRemoteDataSource: ContactRemoteDataSource
    private lateinit var mockContactLocalDataSource: ContactLocalDataSource
    private val pageLoadSize = 20

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
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test item loaded with refresh from locale database`() = runTest {
        coEvery {
            mockContactLocalDataSource.getRequestedContactFromDatabase(
                seed = "default",
                page = 1,
                pageSize = pageLoadSize
            )
        } returns listOf(DataMockConstants.contactATO)

        val pagingSource = ContactPagingSource("default")

        val refreshLoadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = pageLoadSize,
            placeholdersEnabled = false
        )

        val actualLoadResult = pagingSource.load(refreshLoadParams)

        assertTrue(actualLoadResult is PagingSource.LoadResult.Page)
        assertEquals(
            DataMockConstants.contactATO,
            (actualLoadResult as PagingSource.LoadResult.Page).data.first()
        )
        assertEquals(2, actualLoadResult.nextKey)
        coVerify(exactly = 0) {
            mockContactRemoteDataSource.getContacts(
                seed = "default",
                page = 1,
                pageSize = pageLoadSize
            )
        }

        coVerify(exactly = 1) {
            mockContactLocalDataSource.getRequestedContactFromDatabase(
                seed = "default",
                page = 1,
                pageSize = pageLoadSize
            )
        }
    }

    @Test
    fun `test item loaded with refresh from remote datasource`() = runTest {
        coEvery {
            mockContactLocalDataSource.getRequestedContactFromDatabase(
                seed = "default",
                page = 1,
                pageSize = pageLoadSize
            )
        } returns null

        coEvery {
            mockContactRemoteDataSource.getContacts(
                seed = "default",
                page = 1,
                pageSize = pageLoadSize
            )
        } returns DataMockConstants.apiResponseATO


        val pagingSource = ContactPagingSource("default")

        val refreshLoadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = pageLoadSize,
            placeholdersEnabled = false
        )

        val actualLoadResult = pagingSource.load(refreshLoadParams)

        assertTrue(actualLoadResult is PagingSource.LoadResult.Page)
        assertEquals(
            DataMockConstants.contactATO,
            (actualLoadResult as PagingSource.LoadResult.Page).data.first()
        )
        assertEquals(2, actualLoadResult.nextKey)
        coVerify(exactly = 1) {
            mockContactRemoteDataSource.getContacts(
                seed = "default",
                page = 1,
                pageSize = pageLoadSize
            )
        }
        coVerify(exactly = 1) {
            mockContactLocalDataSource.getRequestedContactFromDatabase(
                seed = "default",
                page = 1,
                pageSize = pageLoadSize
            )
        }
        coVerify(exactly = 1) {
            mockContactLocalDataSource.saveContactsToDatabase(
                seed = "default",
                page = 1,
                pageSize = pageLoadSize,
                contacts = listOf(DataMockConstants.contactATO)
            )
        }
    }

    @Test
    fun `load result error with http exception`() = runTest {
        coEvery {
            mockContactLocalDataSource.getRequestedContactFromDatabase(
                seed = "default",
                page = 1,
                pageSize = pageLoadSize
            )
        } throws Exception("Network error")

        val pagingSource = ContactPagingSource("default")

        val refreshLoadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = pageLoadSize,
            placeholdersEnabled = false
        )

        val actualLoadResult = pagingSource.load(refreshLoadParams)

        assertTrue(actualLoadResult is PagingSource.LoadResult.Error)
        coVerify(exactly = 0) {
            mockContactRemoteDataSource.getContacts(
                seed = "default",
                page = 1,
                pageSize = pageLoadSize
            )
        }

        coVerify(exactly = 1) {
            mockContactLocalDataSource.getRequestedContactFromDatabase(
                seed = "default",
                page = 1,
                pageSize = pageLoadSize
            )
        }
    }
}