package com.example.gamelb.db.converters

import androidx.room.TypeConverter
import com.example.gamelb.db.PlatformEntity
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import java.lang.reflect.Type


class PlatformConverter {

    @TypeConverter
    fun fromPlatformEntityListToString(platforms: List<PlatformEntity>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<PlatformEntity?>?>() {}.type
        return gson.toJson(platforms, type)
    }

    @TypeConverter
    fun fromStringToPlatformEntityList(json: String): List<PlatformEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<PlatformEntity?>?>() {}.type
        return gson.fromJson(json, type)
    }
}