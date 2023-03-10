package com.ezatpanah.databinding_paging_youtube.api

import com.ezatpanah.databinding_paging_youtube.response.ResponseTopHeadline
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("top-headlines?country=us&pageSize=10")
    suspend fun getTopHeadlineNews(
        @Query("page") pageNumber: Int
    ): Response<ResponseTopHeadline>

}