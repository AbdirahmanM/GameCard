package com.example.gamelb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gamelb.db.converters.GenreConverter
import com.example.gamelb.db.converters.PlatformConverter
import com.example.gamelb.db.converters.StoreConverter


@Database(entities = [GameEntity::class], version = 5)
@TypeConverters(GenreConverter::class, PlatformConverter::class, StoreConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameEntityDAO(): GameEntityDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

