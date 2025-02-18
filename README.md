# **TimePassGames Assignment**

This repository contains the **TimePassGames Assignment App**, built using **Kotlin** and **Jetpack Compose**. The app generates random images of dogs and saves them in a persistent cache for later viewing.  

---

## **Features**

### 1. **Home Screen**  
- **Buttons:**  
  - **Generate Dogs:** Navigate to the Generate Dogs screen.  
  - **Recently Generated Dogs:** Navigate to the gallery of cached dog images.  

### 2. **Generate Dogs Screen**  
- **Generate Button:**  
  - Fetches a random dog image from the [Dog CEO API](https://dog.ceo/dog-api/).  
  - Displays the fetched image.  
  - Stores the image in an **LRU cache** (limited to 20 images).  
  - Cache is persisted using **RoomDB**, ensuring images are saved across app sessions.  

### 3. **Recently Generated Dogs Screen**  
- **Scrollable Gallery:**  
  - Displays cached dog images in a gallery view.  
- **Clear Dogs Button:**  
  - Clears the cache and the gallery view.  

---

## **Technologies Used**
- **Kotlin**  
- **Jetpack Compose**  
- **RoomDB** for data persistence.  
- **MVVM Architecture** for code organization.  
- **LRU Cache** for managing image storage.  
- **Retrofit** for API requests.  
- **Coroutines** for asynchronous operations.  

---

## **Screenshots**

<p align="center">
  <img src="https://i.postimg.cc/HxVvbfds/1.png" width="150">
  <img src="https://i.postimg.cc/1zWYn9xj/2.png" width="150">
  <img src="https://i.postimg.cc/xChx5t1G/3.png" width="150">
  <img src="https://i.postimg.cc/j5cNdjGD/Demo.gif" width="150">
  
</p>

---

## **How the Solution Works**  

1. **Home Screen:**  
   - Acts as the main hub, providing navigation options to the other two screens.  

2. **Generate Dogs Screen:**  
   - The "Generate!" button triggers an API request to fetch a random dog image.  
   - The fetched image is displayed and added to the LRU cache.  
   - The cache is implemented with a size limit of 20 images, ensuring memory efficiency.  
   - Data is persisted using RoomDB to retain images across app sessions.  

3. **Recently Generated Dogs Screen:**  
   - Displays all cached images in a scrollable gallery.  
   - The "Clear Dogs" button clears the RoomDB cache and the gallery.  

