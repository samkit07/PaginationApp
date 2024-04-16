package com.example.paginationapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PostApiService {

    @GET("posts")
    suspend fun getAllPosts(@Query("_page") page:Int):List<PostModel>

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}