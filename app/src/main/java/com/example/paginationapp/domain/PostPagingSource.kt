package com.example.paginationapp.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationapp.data.remote.PostModel
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostPagingSource @Inject constructor(val repository: PostRepository) : PagingSource<Int, PostModel>() {
    override fun getRefreshKey(state: PagingState<Int, PostModel>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostModel> {
        val page = params.key?:1
        delay(2000L)
        val response = repository.getPosts(page)
        return try {
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        }
        catch (e: IOException){
            LoadResult.Error(e)
        }
        catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}