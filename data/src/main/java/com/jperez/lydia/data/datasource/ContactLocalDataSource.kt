package com.jperez.lydia.data.datasource

import com.jperez.lydia.data.model.ContactATO

interface ContactLocalDataSource {

    /**
     * Retrieves a list of contacts from the database based on the provided seed, page, and page size.
     *
     * @param seed The seed value used to filter contacts.
     * @param page The page number for pagination.
     * @param pageSize The number of contacts per page.
     * @return A list of ContactATO objects or null if no contacts are found.
     */
    suspend fun getRequestedContactFromDatabase(
        seed: String,
        page: Int,
        pageSize: Int
    ): List<ContactATO>?

    /**
     * Saves a list of contacts to the database.
     *
     * @param seed The seed value used to identify the contacts.
     * @param page The page number for pagination.
     * @param pageSize The number of contacts per page.
     * @param contacts The list of ContactATO objects to be saved.
     */
    suspend fun saveContactsToDatabase(
        seed: String,
        page: Int,
        pageSize: Int,
        contacts: List<ContactATO>
    )
}