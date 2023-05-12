package com.example.rightnewsapp.data.api.instance

import com.example.rightnewsapp.data.api.service.NewsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Consts {
    const val BASE_URL = "https://newsapi.org/"
    const val END_POINT_DAILY = "v2/top-headlines"
    const val API_KEY = "3767b0e568ef48b488624acd0ec763d0"
    const val END_POINT_SEARCH = "v2/everything"

}