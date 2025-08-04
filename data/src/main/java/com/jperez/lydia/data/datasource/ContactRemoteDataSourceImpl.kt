package com.jperez.lydia.data.datasource

import com.jperez.lydia.data.api.ApiClient
import com.jperez.lydia.data.model.APIResponseATO
import org.koin.java.KoinJavaComponent.inject

class ContactRemoteDataSourceImpl : ContactRemoteDataSource {
    private val apiClient: ApiClient by inject(ApiClient::class.java)

    override suspend fun getContacts(seed: String, page: Int, pageSize: Int): APIResponseATO {
        val response = apiClient.getContacts(seed, page, pageSize)
        return response
    }
}
