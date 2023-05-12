package com.example.rightnewsapp.domain.repos

import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.data.db.dao.NewsDao
import javax.inject.Inject

class DbRepo @Inject constructor(val dao: NewsDao) {
    suspend fun insertNew(article: Article) = dao.insertNew(article)
    suspend fun deleteNew(article: Article) = dao.deleteNew(article)
    fun getAllNews() = dao.getAllDbNews()
}