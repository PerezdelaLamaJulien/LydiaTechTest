package com.jperez.lydia.data.api

import com.jperez.lydia.data.model.APIResponseATO
import com.jperez.lydia.data.model.APIResponseInfoATO
import com.jperez.lydia.data.model.ContactATO
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactApiService {

    /**
     * Get a list of contacts from the API.
     *
     * @param seed A seed value to generate a consistent set of contacts.
     * @return An [APIResponseATO] containing the list of contacts [ContactATO] and info of call [APIResponseInfoATO] .
     */
    @GET("1.3/")
    suspend fun getContacts(@Query("seed") seed: String, @Query("page") page: Int, @Query("results") pageSize: Int): APIResponseATO

}