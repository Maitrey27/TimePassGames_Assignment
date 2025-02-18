package com.example.timepassgames.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.timepassgames.viewmodel.GenerateDogsViewModel
@OptIn(ExperimentalMaterial3Api::class) // Marking experimental API usage
@Composable
fun GenerateDogsScreen(
    onNavigateBack: () -> Unit,
    viewModel: GenerateDogsViewModel
) {
    // Define the button color
    val buttonColor = Color(66, 134, 244)

    // Collect state from ViewModel
    val imageUrl by viewModel.imageUrl.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // Get screen height for image size
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Scaffold(
        containerColor = Color.White, // Set the background color of the Scaffold
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable(
                                    onClick = onNavigateBack,
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                                .offset(x = (-8).dp)
                                .padding(start = 0.dp, end = 50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                tint = Color(66, 134, 244),
                                modifier = Modifier.size(24.dp) // Explicit size for the icon
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Back",
                                color = Color(66, 134, 244),
                                fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                            )
                        }
                        Text(
                            text = "Generate Dogs!",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 18.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    scrolledContainerColor = Color.White
                ),
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(0.dp)
                    )
                    .height(76.dp),
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image Area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight / 2)
                ) {
                    when {
                        isLoading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        errorMessage != null -> {
                            Text(
                                text = errorMessage ?: "Unknown Error",
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.Center)
                            )
                            Log.e("GenerateDogsScreen", "Error: $errorMessage")
                        }
                        imageUrl != null -> {
                            Image(
                                painter = rememberAsyncImagePainter(imageUrl),
                                contentDescription = "Dog Image",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        else -> {


                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Generate Button
                Box(
                    modifier = Modifier
                        .size(width = 140.dp, height = 35.dp)
                        .border(
                            width = 3.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(50.dp)
                        )
                ) {
                    Button(
                        onClick = { viewModel.generateDogImage() },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = "Generate!", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    )
}
