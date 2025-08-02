package com.jperez.lydia.data.datasource

import com.jperez.lydia.data.model.APIResponseATO

interface ContactRemoteDataSource {

    /**
     * Get a response from the API.
     *
     * @param seed A seed value to generate a consistent set of contacts.
     * @param page The page number to retrieve.
     * @param pageSize The number of contacts per page.
     * @return A list of [APIResponseATO] objects.
     */
    suspend fun getContacts(seed: String, page: Int, pageSize: Int): APIResponseATO
}