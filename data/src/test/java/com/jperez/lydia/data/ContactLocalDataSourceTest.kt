package com.jperez.lydia.data

import com.jperez.lydia.data.database.ContactDao
import com.jperez.lydia.data.database.PaginationInfoDao
import com.jperez.lydia.data.datasource.ContactLocalDataSource
import com.jperez.lydia.data.datasource.ContactLocalDataSourceImpl
import com.jperez.lydia.data.mapper.ContactAtoEntityMapper
import io.mockk.coEvery
import io.mockk.coVerify
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
 * Unit tests for the ContactLocalDataSource class.
 */
class ContactLocalDataSourceTest : KoinTest {
    private lateinit var mockContactDao: ContactDao
    private lateinit var mockPaginationInfoDao: PaginationInfoDao
    private lateinit var mockContactAtoEntityMapper: ContactAtoEntityMapper
    private lateinit var localDataSource: ContactLocalDataSource

    @Before
    fun setUp() {
        mockContactDao = mockk(relaxed = true)
        mockPaginationInfoDao = mockk(relaxed = true)
        mockContactAtoEntityMapper = mockk(relaxed = true)
        localDataSource = ContactLocalDataSourceImpl()

        startKoin {
            modules(
                module {
                    single<ContactDao> { mockContactDao }
                    single<PaginationInfoDao> { mockPaginationInfoDao }
                    single<ContactAtoEntityMapper> { mockContactAtoEntityMapper }
                })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `paginationInfo findByInfo return null so result is null`() = runTest {
        coEvery { mockPaginationInfoDao.findByInfo(seed = "default",  page= 1, pageSize = 10) } returns null

        val result = localDataSource.getRequestedContactFromDatabase("default",  page= 1, pageSize = 10)

        assertNull(result)
        coVerify(exactly = 0) {
            mockContactDao.findByPaginationInfo(
                paginationInfoKey = any()
            )
        }
    }

    @Test
    fun `paginationInfo is save so get function return list of contact`() = runTest {
        coEvery { mockPaginationInfoDao.findByInfo(seed = "default",  page= 1, pageSize = 10) } returns DataMockConstants.paginationInfoEntity
        coEvery { mockContactDao.findByPaginationInfo("pagination-id") } returns listOf(DataMockConstants.contactEntity)
        coEvery { mockContactAtoEntityMapper.mapEntityListToATOList(listOf(DataMockConstants.contactEntity)) } returns listOf(DataMockConstants.contactATO)

        val result = localDataSource.getRequestedContactFromDatabase("default",  page= 1, pageSize = 10)
        assertEquals(DataMockConstants.contactATO, result?.first())
    }

    @Test
    fun `paginationInfo findByInfo throw exception return null`() = runTest {
        coEvery { mockPaginationInfoDao.findByInfo(seed = "default",  page= 1, pageSize = 10) } throws Exception("Database error")

        val result = localDataSource.getRequestedContactFromDatabase("default",  page= 1, pageSize = 10)

        assertNull(result)
        coVerify(exactly = 0) {
            mockContactDao.findByPaginationInfo(
                paginationInfoKey = any()
            )
        }
    }

    @Test
    fun `paginationInfo findByPaginationInfo throw exception return null`() = runTest {
        coEvery { mockPaginationInfoDao.findByInfo(seed = "default",  page= 1, pageSize = 10) } returns DataMockConstants.paginationInfoEntity
        coEvery { mockContactDao.findByPaginationInfo("pagination-id") } throws Exception("Database error")
        coEvery { mockContactAtoEntityMapper.mapEntityListToATOList(listOf(DataMockConstants.contactEntity)) } returns listOf(DataMockConstants.contactATO)

        val result = localDataSource.getRequestedContactFromDatabase("default",  page= 1, pageSize = 10)
        assertNull(result)
    }

    @Test
    fun `paginationInfo save call daos`() = runTest {
        coEvery { mockPaginationInfoDao.insertAll(any()) } answers {}
        coEvery { mockContactAtoEntityMapper.mapATOListToEntityList(listOf(DataMockConstants.contactATO), paginationInfoId = any()) } returns listOf(DataMockConstants.contactEntity)
        coEvery { mockContactDao.insertAll(any()) } answers {}
         localDataSource.saveContactsToDatabase("default",  page= 1, pageSize = 10, contacts = listOf(DataMockConstants.contactATO))

        coVerify {
            mockPaginationInfoDao.insertAll(any())
        }
        coVerify {
            mockContactDao.insertAll(any())
        }
    }
}