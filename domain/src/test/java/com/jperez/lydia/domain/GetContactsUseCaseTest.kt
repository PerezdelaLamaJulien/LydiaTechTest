package com.jperez.lydia.domain

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.domain.mapper.ContactMapper
import com.jperez.lydia.domain.usecase.GetContactsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
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
 * Unit tests for the GetContactsUseCase class.
 */
class GetContactsUseCaseTest : KoinTest {
    private lateinit var mockRepository: ContactRepository
    private lateinit var mockContactMapper: ContactMapper
    private lateinit var getContactsUseCase: GetContactsUseCase

    @Before
    fun setUp() {
        mockRepository = mockk(relaxed = true)
        mockContactMapper = mockk(relaxed = true)
        getContactsUseCase = GetContactsUseCase()
        startKoin {
            modules(
                module {
                    single<ContactRepository> { mockRepository }
                    single<ContactMapper> { mockContactMapper }
                })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get contacts`() = runTest {
        val flow = flowOf(PagingData.from(listOf(DomainMockConstants.contactATO)))

        coEvery { mockRepository.getContacts("seed") } returns flow
        coEvery { mockContactMapper.mapTo(DomainMockConstants.contactATO) } returns DomainMockConstants.contact
        val result = getContactsUseCase.execute("seed")
        assertEquals(1, flow.asSnapshot {  }.size)

    }
}