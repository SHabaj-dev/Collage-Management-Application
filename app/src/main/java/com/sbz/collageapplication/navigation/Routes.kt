package com.sbz.collageapplication.navigation

sealed class Routes (val route: String){
    object Home: Routes("home")
    object Faculty: Routes("faculty")
    object AboutUs: Routes("about_us")
    object Gallery: Routes("gallery")
    object BottomNav: Routes("bottom_nav")
}