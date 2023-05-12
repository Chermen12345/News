package com.example.rightnewsapp.data.api.service


import com.example.rightnewsapp.data.api.instance.Consts.END_POINT_DAILY
import com.example.rightnewsapp.data.api.instance.Consts.END_POINT_SEARCH
import com.example.rightnewsapp.data.api.pojo.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET(END_POINT_DAILY)
    suspend fun getAllDailyNews(@Query("country") country: String,
                                @Query("apiKey") apikey: String): Response<NewsResponse>
    @GET(END_POINT_SEARCH)
    suspend fun searchAllNews(@Query ("q") searchQ: String, @Query("apiKey") apikey: String):
            Response<NewsResponse>
    @GET(END_POINT_SEARCH)
    suspend fun getOneCategoryNews(@Query ("q") categoryQ: String, @Query("apiKey") apikey: String):
            Response<NewsResponse>
}