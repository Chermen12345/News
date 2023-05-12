package com.example.rightnewsapp.data.db.instance

import android.content.Context
import androidx.room.Room
import com.example.rightnewsapp.data.api.pojo.Article
import com.example.rightnewsapp.data.api.pojo.Source
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InstanceModule {
    @Provides
    @Singleton
    fun getDb(@ApplicationContext context: Context) = Room.databaseBuilder(context,
    Instance::class.java, "news.db").build()
    @Provides
    @Singleton
    fun getDao(db: Instance) = db.getDao()

}