package com.sbz.collageapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sbz.collageapplication.admin.screens.AdminDashboard
import com.sbz.collageapplication.admin.screens.ManageBanner
import com.sbz.collageapplication.admin.screens.ManageCollageInfo
import com.sbz.collageapplication.admin.screens.ManageFaculty
import com.sbz.collageapplication.admin.screens.ManageGallery
import com.sbz.collageapplication.screens.AboutUs
import com.sbz.collageapplication.screens.BottomNav
import com.sbz.collageapplication.screens.Faculty
import com.sbz.collageapplication.screens.Gallery
import com.sbz.collageapplication.screens.Home
import com.sbz.collageapplication.utils.Constants.isAdmin

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = if (isAdmin) Routes.AdminDashboard.route else Routes.BottomNav.route
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

        composable(Routes.AdminDashboard.route) {
            AdminDashboard(navController)
        }

        composable(Routes.ManageBanner.route) {
            ManageBanner(navController)
        }

        composable(Routes.ManageFaculty.route) {
            ManageFaculty()
        }

        composable(Routes.ManageGallery.route) {
            ManageGallery()
        }

        composable(Routes.ManageCollageInfo.route) {
            ManageCollageInfo()
        }
    }
}