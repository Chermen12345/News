package com.example.rightnewsapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rightnewsapp.data.api.pojo.Article

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNew(article: Article)
    @Delete
    suspend fun deleteNew(article: Article)
    @Query("SELECT * FROM news_table")
    fun getAllDbNews(): LiveData<List<Article>>
}