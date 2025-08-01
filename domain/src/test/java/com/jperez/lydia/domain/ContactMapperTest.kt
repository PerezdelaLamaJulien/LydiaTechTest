package com.jperez.lydia.domain

import com.jperez.lydia.domain.mapper.ContactMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Unit tests for the ContactMapper class.
 */
class ContactMapperTest : KoinTest {
    private val mockContactMapper: ContactMapper = ContactMapper()

    /**
     * Tests the mapping of a ContactATO object to a Contact object.
     * It verifies that the properties of the Contact object match the expected values.
     */
    @Test
    fun `map contactATO to contact`() = runTest {
        val result = mockContactMapper.mapTo(DomainMockConstants.contactATO)
        assertEquals(DomainMockConstants.contact, result)
    }
}