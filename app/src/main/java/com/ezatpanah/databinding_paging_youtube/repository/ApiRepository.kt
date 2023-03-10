package com.ezatpanah.databinding_paging_youtube.repository

import com.ezatpanah.databinding_paging_youtube.api.ApiServices
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun getTopHeadlineNews(pageNumber: Int) = apiServices.getTopHeadlineNews(pageNumber)

}