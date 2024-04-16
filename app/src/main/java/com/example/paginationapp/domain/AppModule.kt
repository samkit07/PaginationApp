package com.example.paginationapp.domain

import com.example.paginationapp.data.remote.PostApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePostApi(): PostApiService{
        return Retrofit.Builder()
            .baseUrl(PostApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApiService::class.java)
    }
}