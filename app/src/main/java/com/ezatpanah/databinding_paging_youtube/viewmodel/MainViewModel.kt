package com.ezatpanah.databinding_paging_youtube.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ezatpanah.databinding_paging_youtube.paging.NewsPagingSource
import com.ezatpanah.databinding_paging_youtube.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    val newsList = Pager(PagingConfig(1)) {
        NewsPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}