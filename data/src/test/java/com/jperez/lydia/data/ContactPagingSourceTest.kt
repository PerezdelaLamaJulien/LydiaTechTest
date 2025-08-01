package com.jperez.lydia.data

import androidx.paging.PagingSource
import com.jperez.lydia.data.datasource.ContactContactRemoteDataSource
import com.jperez.lydia.data.paging.ContactPagingSource
import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.data.repository.ContactRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    private lateinit var mockContactContactRemoteDataSource: ContactContactRemoteDataSource
    private lateinit var repository: ContactRepository

    private val pageLoadSize = 20
    private val totalPage = 3

    @Before
    fun setUp() {
        mockContactContactRemoteDataSource = mockk(relaxed = true)
        repository = ContactRepositoryImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test item loaded with refresh`() = runTest {
        startKoin {
            modules(
                module {
                    single<ContactContactRemoteDataSource> { mockContactContactRemoteDataSource }
                })
        }

        coEvery { mockContactContactRemoteDataSource.getContacts(
            seed = "default",
            page = 1,
            pageSize = pageLoadSize
        ) } returns DataMockConstants.apiResponseATO

        val pagingSource = ContactPagingSource("default")

        val refreshLoadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = pageLoadSize,
            placeholdersEnabled = false
        )

        val actualLoadResult = pagingSource.load(refreshLoadParams)

        assertTrue(actualLoadResult is PagingSource.LoadResult.Page)
        assertEquals(DataMockConstants.contactATO, (actualLoadResult as PagingSource.LoadResult.Page).data.first() )
        assertEquals(2, actualLoadResult.nextKey )
    }

    @Test
    fun `load result error with http exception`() = runTest {
        startKoin {
            modules(
                module {
                    single<ContactContactRemoteDataSource> { mockContactContactRemoteDataSource }
                })
        }

        coEvery { mockContactContactRemoteDataSource.getContacts(
            seed = "default",
            page = 1,
            pageSize = pageLoadSize
        ) } throws Exception("Network error")

        val pagingSource = ContactPagingSource("default")

        val refreshLoadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = pageLoadSize,
            placeholdersEnabled = false
        )

        val actualLoadResult = pagingSource.load(refreshLoadParams)

        assertTrue(actualLoadResult is PagingSource.LoadResult.Error)
    }
}