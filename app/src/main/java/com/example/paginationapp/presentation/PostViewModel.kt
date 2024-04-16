package com.example.paginationapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paginationapp.data.remote.PostModel
import com.example.paginationapp.domain.PostPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postPagingSource: PostPagingSource): ViewModel() {
    private val _postResponse:MutableStateFlow<PagingData<PostModel>> = MutableStateFlow(PagingData.empty())
    var postResponse = _postResponse.asStateFlow()
        private set

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ){
                postPagingSource
            }.flow.cachedIn(viewModelScope).collect(){
                _postResponse.value = it
            }
        }
    }
}