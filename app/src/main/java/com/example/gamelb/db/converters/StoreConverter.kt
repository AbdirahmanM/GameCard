package com.example.gamelb.db.converters

import androidx.room.TypeConverter
import com.example.gamelb.db.PlatformEntity
import com.example.gamelb.db.StoreEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class StoreConverter {

    @TypeConverter
    fun fromStoreEntityListToString(stores: List<StoreEntity>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<StoreEntity?>?>() {}.type
        return gson.toJson(stores, type)
    }

    @TypeConverter
    fun fromStringToStoreEntityList(json: String): List<StoreEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<StoreEntity?>?>() {}.type
        return gson.fromJson(json, type)
    }
}