package com.example.rightnewsapp.data.api.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "news_table")
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    @PrimaryKey
    val url: String,
    val urlToImage: String?
)