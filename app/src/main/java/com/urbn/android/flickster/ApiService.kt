package com.urbn.android.flickster

import com.urbn.android.flickster.network.model.ApiResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun getData(
        @Query("q") q: String,
        @Query("format") format: String
    ): ApiResponseData
}