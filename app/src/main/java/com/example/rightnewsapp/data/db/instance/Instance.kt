package com.example.rightnewsapp.data.db.instance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.data.db.dao.NewsDao
import com.example.rightnewsapp.data.db.typeconvs.TypeConv

@Database(entities = [Article::class], version = 1)
@TypeConverters(TypeConv::class)
abstract class Instance(): RoomDatabase() {
    abstract fun getDao(): NewsDao
}