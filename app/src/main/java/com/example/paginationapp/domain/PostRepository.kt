package com.example.paginationapp.domain

import com.example.paginationapp.data.remote.PostApiService
import com.example.paginationapp.data.remote.PostModel
import javax.inject.Inject

class PostRepository @Inject constructor(private val postApiService: PostApiService) {
    suspend fun getPosts(page:Int) : List<PostModel> = postApiService.getAllPosts(page)
}