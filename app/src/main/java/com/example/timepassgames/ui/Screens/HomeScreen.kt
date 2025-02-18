// ui/screens/HomeScreen.kt
package com.example.timepassgames.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timepassgames.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onNavigateToGenerateDogs: () -> Unit,
    onNavigateToRecentlyGeneratedDogs: () -> Unit,
    viewModel: HomeViewModel
) {
    // Define the button color
    val buttonColor = Color(66, 134, 244)



    // Center the buttons vertically and horizontally
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title text
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 200.dp), // Adjust the space between text and buttons
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Random Dog Generator!",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600
            )
        }

        // Generate Dogs Button
        Box(
            modifier = Modifier
                .size(width = 180.dp, height = 40.dp) // Adjust overall button size
                .border(
                    width = 3.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(50.dp) // Match button shape
                )
        ) {
            Button(
                onClick = onNavigateToGenerateDogs,
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(50.dp), // Match button shape
                modifier = Modifier.fillMaxSize() // Ensures button fills the enclosing box
            ) {
                Text(text = "Generate Dogs!", color = Color.White, fontSize = 16.sp)
            }
        }


        Spacer( Modifier.height(13.dp))
        Box(
            modifier = Modifier
                .size(width = 300.dp, height = 40.dp) // Adjust overall button size
                .border(
                    width = 3.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(50.dp) // Match button shape
                )
        ) {
            Button(
                onClick = onNavigateToRecentlyGeneratedDogs,
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(50.dp), // Match button shape
                modifier = Modifier.fillMaxSize() // Ensures button fills the enclosing box
            ) {
                Text(
                    text = "My Recently Generated Dogs!",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

    }
}
