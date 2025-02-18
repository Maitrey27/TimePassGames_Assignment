// data/repository/DogRepository.kt
package com.example.timepassgames.data.repository

import com.example.timepassgames.data.model.DogImage
import kotlinx.coroutines.flow.Flow

class DogRepository(private val dogImageDao: DogImageDao) {

    fun getRecentDogImages(): Flow<List<DogImage>> {
        return dogImageDao.getRecentDogImages()
    }

    suspend fun insertDogImage(dogImage: DogImage) {
        dogImageDao.insertDogImage(dogImage)
    }

    suspend fun clearDogImages() {
        dogImageDao.clearDogImages()
    }
}
