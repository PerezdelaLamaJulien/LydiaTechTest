package com.jperez.lydia.feature

import com.jperez.lydia.feature.mapper.ContactDetailUIMapper
import com.jperez.lydia.feature.mapper.ContactListItemUIMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Unit tests for the ContactListItemUIMapper class.
 */
class ContactDetailUIMapperTest : KoinTest {
    private val mapper = ContactDetailUIMapper()

    @Test
    fun `map contact to ContactListItemUI`() = runTest {
        val result = mapper.mapTo(FeatureMockConstants.contact)
        assertEquals(FeatureMockConstants.contactDetailUI, result)
    }
}