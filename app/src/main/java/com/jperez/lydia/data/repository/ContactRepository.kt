package com.jperez.lydia.data.repository

import com.jperez.lydia.data.model.ContactATO

interface ContactRepository {

    suspend fun getContacts(seed: String): List<ContactATO>
}