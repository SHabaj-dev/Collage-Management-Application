package com.sbz.collageapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sbz.collageapplication.screens.AboutUs
import com.sbz.collageapplication.screens.BottomNav
import com.sbz.collageapplication.screens.Faculty
import com.sbz.collageapplication.screens.Gallery
import com.sbz.collageapplication.screens.Home

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.BottomNav.route
    ) {
        composable(Routes.BottomNav.route) {
            BottomNav(navController)
        }

        composable(Routes.Home.route) {
            Home()
        }

        composable(Routes.Gallery.route) {
            Gallery()
        }

        composable(Routes.AboutUs.route) {
            AboutUs()
        }

        composable(Routes.Faculty.route) {
            Faculty()
        }
    }
}