// data/repository/DogImageDao.kt
package com.example.timepassgames.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.timepassgames.data.model.DogImage
import kotlinx.coroutines.flow.Flow

@Dao
interface DogImageDao {
    @Query("SELECT * FROM dog_images ORDER BY id DESC LIMIT 20")
    fun getRecentDogImages(): Flow<List<DogImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogImage(dogImage: DogImage)

    @Query("DELETE FROM dog_images")
    suspend fun clearDogImages()
}
