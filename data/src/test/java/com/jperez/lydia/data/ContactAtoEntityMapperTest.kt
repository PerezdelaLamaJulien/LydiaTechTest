package com.jperez.lydia.data

import com.jperez.lydia.data.mapper.ContactAtoEntityMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Unit tests for the ContactAtoEntityMapper class.
 */
class ContactAtoEntityMapperTest : KoinTest {
    private val mapper: ContactAtoEntityMapper = ContactAtoEntityMapper()

    @Test
    fun `map ATO to Entity`() = runTest {
        val result = mapper.mapATOListToEntityList(
            listOf(DataMockConstants.contactATO),
            DataMockConstants.apiResponseInfoATO.seed
        )
        assertEquals(DataMockConstants.contactEntity, result.first())
    }

    @Test
    fun `map Entity to ATO`() = runTest {
        val result = mapper.mapEntityListToATOList(
            listOf(DataMockConstants.contactEntity),
        )
        assertEquals(DataMockConstants.contactATO, result.first())
    }
}