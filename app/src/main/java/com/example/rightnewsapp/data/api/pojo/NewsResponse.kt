package com.example.rightnewsapp.data.api.pojo

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)