package com.jperez.lydia.data.api

import com.jperez.lydia.data.model.APIResponseATO
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactApiService {

    @GET("1.3/")
    suspend fun getContacts(@Query("seed") seed: String): APIResponseATO

}