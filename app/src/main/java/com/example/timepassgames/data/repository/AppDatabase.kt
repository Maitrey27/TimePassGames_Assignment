// data/repository/AppDatabase.kt
package com.example.timepassgames.data.repository

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.timepassgames.data.model.DogImage

@Database(entities = [DogImage::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogImageDao(): DogImageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "dog_image_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
