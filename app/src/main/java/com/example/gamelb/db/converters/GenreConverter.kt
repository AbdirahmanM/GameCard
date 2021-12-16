package com.example.gamelb.db.converters

import androidx.room.TypeConverter
import com.example.gamelb.db.GenreEntity
import com.example.gamelb.db.PlatformEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class GenreConverter {

    @TypeConverter
    fun fromGenreEntityListToString(genres: List<GenreEntity>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<GenreEntity?>?>() {}.type
        return gson.toJson(genres, type)
    }

    @TypeConverter
    fun fromStringToGenreEntityList(json: String): List<GenreEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<GenreEntity?>?>() {}.type
        return gson.fromJson(json, type)
    }
}