package com.jperez.lydia.feature

import com.jperez.lydia.feature.mapper.ContactListItemUIMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Unit tests for the ContactListItemUIMapper class.
 */
class ContactListItemUIMapperTest : KoinTest {
    private val mapper = ContactListItemUIMapper()

    @Test
    fun `map contact to ContactListItemUI`() = runTest {
        val result = mapper.mapTo(FeatureMockConstants.contact)
        assertEquals(FeatureMockConstants.contactListItemUI, result)
    }
}