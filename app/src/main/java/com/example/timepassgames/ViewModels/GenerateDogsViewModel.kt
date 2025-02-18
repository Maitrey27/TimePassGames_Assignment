package com.example.timepassgames.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.timepassgames.data.model.DogImage
import com.example.timepassgames.data.repository.AppDatabase
import com.example.timepassgames.data.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.UnknownHostException

class GenerateDogsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DogRepository
    private val json = Json { ignoreUnknownKeys = true }

    init {
        val dogImageDao = AppDatabase.getDatabase(application).dogImageDao()
        repository = DogRepository(dogImageDao)
    }

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl: StateFlow<String?> = _imageUrl

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val client = OkHttpClient.Builder()
        .build()

    fun generateDogImage() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _imageUrl.value = null  // Reset image URL before fetching new one

            try {
                val dogImage = withContext(Dispatchers.IO) {
                    fetchDogImage()
                }

                dogImage?.let {
                    _imageUrl.value = it.message
                    // Save to database
                    try {
                        repository.insertDogImage(DogImage(imageUrl = it.message))
                    } catch (e: Exception) {
                        _errorMessage.value = "Failed to save image: ${e.localizedMessage}"
                    }
                }
            } catch (e: UnknownHostException) {
                _errorMessage.value = "No internet connection. Please check your network."
            } catch (e: IOException) {
                _errorMessage.value = "Network error: ${e.localizedMessage}"
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage ?: "Unknown error occurred"}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private suspend fun fetchDogImage(): DogResponse? {
        val request = Request.Builder()
            .url("https://dog.ceo/api/breeds/image/random")
            .build()

        return try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.string()?.let { responseBody ->
                    try {
                        json.decodeFromString<DogResponse>(responseBody)
                    } catch (e: Exception) {
                        throw Exception("Failed to parse response: ${e.localizedMessage}")
                    }
                } ?: throw Exception("Empty response from server")
            } else {
                throw IOException("Server returned error: ${response.code}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    @Serializable
    data class DogResponse(
        val message: String,
        val status: String
    )
}