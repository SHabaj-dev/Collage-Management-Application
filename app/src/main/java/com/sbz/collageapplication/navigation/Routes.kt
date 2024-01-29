package com.sbz.collageapplication.navigation

sealed class Routes (val route: String){
    object Home : Routes("home")
    object Faculty : Routes("faculty")
    object AboutUs : Routes("about_us")
    object Gallery : Routes("gallery")
    object BottomNav : Routes("bottom_nav")
    object AdminDashboard : Routes("admin_dashboard")
    object ManageBanner : Routes("manage_banner")
    object ManageCollageInfo : Routes("manage_collage_info")
    object ManageFaculty : Routes("manage_faculty")
    object ManageGallery : Routes("manage_gallery")
}