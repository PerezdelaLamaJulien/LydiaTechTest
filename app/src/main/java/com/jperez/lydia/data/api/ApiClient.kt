package com.jperez.lydia.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jperez.lydia.data.model.APIResponseATO
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private const val BASE_URL =
    "https://randomuser.me/api/"

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
class ApiClient {
    val retrofitService: ContactApiService by lazy {
        retrofit.create(ContactApiService::class.java)
    }

    suspend fun getContacts(seed: String): APIResponseATO {
        return retrofitService.getContacts(seed)
    }
}