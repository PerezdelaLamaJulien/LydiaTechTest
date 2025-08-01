package com.jperez.lydia.feature

import com.jperez.lydia.feature.mapper.ListContactItemUIMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Unit tests for the ListContactItemUIMapper class.
 */
class ListContactItemUIMapperTest : KoinTest {
    private val mockContactMapper: ListContactItemUIMapper = ListContactItemUIMapper()

    @Test
    fun `map contact to ListContactItemUI`() = runTest {
        val result = mockContactMapper.mapTo(FeatureMockConstants.contact)
        assertEquals(FeatureMockConstants.listContactItemUI, result)
    }
}