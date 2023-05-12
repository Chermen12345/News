package com.example.rightnewsapp.data.api.instance


import com.example.rightnewsapp.data.api.instance.Consts.BASE_URL
import com.example.rightnewsapp.data.api.service.NewsService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitInstance {

    @Provides
    fun providesBaseUrl() = BASE_URL
    @Provides
    @Singleton
    fun providesApiInstance(): NewsService {
        val inter = HttpLoggingInterceptor()
        inter.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(inter).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsService::class.java) }

}