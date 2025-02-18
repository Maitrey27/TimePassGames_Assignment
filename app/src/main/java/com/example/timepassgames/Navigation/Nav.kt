// navigation/NavGraph.kt
package com.example.timepassgames.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*

import com.example.timepassgames.ui.screens.GenerateDogsScreen
import com.example.timepassgames.ui.screens.HomeScreen
import com.example.timepassgames.ui.screens.RecentlyGeneratedDogsScreen
import com.example.timepassgames.viewmodel.GenerateDogsViewModel
import com.example.timepassgames.viewmodel.RecentlyGeneratedDogsViewModel
import com.example.timepassgames.viewmodel.HomeViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "/home",
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(600)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(400)
            )
        },


        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(400)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(400)
            )
        }
    ) {
        composable(route="/home") {
            val homeViewModel: HomeViewModel = viewModel()
            HomeScreen(
                onNavigateToGenerateDogs = { navController.navigate("/generateDogs") },
                onNavigateToRecentlyGeneratedDogs = { navController.navigate("/recentlyGeneratedDogs") },
                viewModel=homeViewModel
            )
        }

        composable("/generateDogs") {
            val generateDogsViewModel: GenerateDogsViewModel = viewModel()
            GenerateDogsScreen(
                onNavigateBack = { navController.popBackStack() },
                viewModel = generateDogsViewModel
            )
        }

        composable("/recentlyGeneratedDogs") {
            val recentlyGeneratedDogsViewModel: RecentlyGeneratedDogsViewModel = viewModel()
            RecentlyGeneratedDogsScreen(
                onNavigateBack = { navController.popBackStack() },
                viewModel = recentlyGeneratedDogsViewModel
            )
        }
    }
}