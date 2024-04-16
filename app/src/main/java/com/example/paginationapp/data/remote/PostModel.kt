package com.example.paginationapp.data.remote

import java.io.Serializable

data class PostModel(
    val userId: String,
    val id: Int,
    val title: String,
    val body: String
): Serializable