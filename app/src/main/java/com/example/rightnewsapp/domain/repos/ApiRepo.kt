package com.example.rightnewsapp.domain.repos

import com.example.rightnewsapp.data.api.service.NewsService
import javax.inject.Inject

class ApiRepo @Inject constructor(val newsService: NewsService) {
    suspend fun getAllDailyNews(country: String, apikey: String) =
        newsService.getAllDailyNews(country, apikey)
    suspend fun searchAllNews(q: String, apikey: String) =
        newsService.searchAllNews(q, apikey)
    suspend fun getOneCategoryNews(q: String, apikey: String) = newsService.getOneCategoryNews(q,apikey)
}