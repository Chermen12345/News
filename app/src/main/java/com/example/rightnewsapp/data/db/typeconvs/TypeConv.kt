package com.example.rightnewsapp.data.db.typeconvs

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.rightnewsapp.data.api.pojo.Source


class TypeConv {
    @TypeConverter
    fun fromSourceToString(source: Source) = source.name
    @TypeConverter
    fun fromStringToSource(name: String) = Source(name, name)
}