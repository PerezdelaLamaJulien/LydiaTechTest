package com.jperez.lydia.data.repository

import com.jperez.lydia.data.model.ContactATO

interface ContactRepository {

    /**
     * Get a list of contacts from the API.
     *
     * @param seed A seed value to generate a consistent set of contacts.
     * @return A list of [ContactATO] objects.
     */
    suspend fun getContacts(seed: String): List<ContactATO>
}