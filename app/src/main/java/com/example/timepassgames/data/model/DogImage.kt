// data/model/DogImage.kt
package com.example.timepassgames.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_images")
data class DogImage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUrl: String
)
