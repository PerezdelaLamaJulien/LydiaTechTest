package com.jperez.lydia.data.repository

import androidx.paging.PagingData
import com.jperez.lydia.data.model.ContactATO
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    /**
     * Get a list of contacts from the datasource.
     *
     * @param seed A seed value to generate a consistent set of contacts.
     * @return A list of [ContactATO] objects.
     */
    suspend fun getContacts(seed: String): Flow<PagingData<ContactATO>>

    /**
     * Get a list of saved seeds from the datasource.
     *
     * @return A list of strings representing saved seeds.
     */
    suspend fun getSavedSeeds(): List<String>


    /**
     * Delete saved seed from the database
     *
     * @param seed The seed to be deleted.
     */
    suspend fun deleteSavedSeed(seed: String)
}