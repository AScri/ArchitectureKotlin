package com.ascri.test.data.dataSources.remote

import com.ascri.test.data.models.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestAPI {
    @GET("api/users")
    suspend fun getImagesAsync(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<ImageResponse>
}