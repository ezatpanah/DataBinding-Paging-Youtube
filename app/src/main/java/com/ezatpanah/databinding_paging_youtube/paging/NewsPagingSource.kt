package com.ezatpanah.databinding_paging_youtube.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ezatpanah.databinding_paging_youtube.repository.ApiRepository
import com.ezatpanah.databinding_paging_youtube.response.ResponseTopHeadline
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val repository: ApiRepository
) : PagingSource<Int, ResponseTopHeadline.Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseTopHeadline.Article> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getTopHeadlineNews(currentPage)
            val data = response.body()!!.articles
            val responseData = mutableListOf<ResponseTopHeadline.Article>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ResponseTopHeadline.Article>): Int? {
        return null
    }
}