package com.jperez.lydia.data.repository

import com.jperez.lydia.data.api.ApiClient
import com.jperez.lydia.data.model.ContactATO
import org.koin.java.KoinJavaComponent.inject

class ContactRepositoryImpl : ContactRepository {
    private val apiClient: ApiClient by inject(ApiClient::class.java)

    override suspend fun getContacts(seed: String): List<ContactATO> {
        val response = apiClient.getContacts(seed)
        return response.results
    }
}
