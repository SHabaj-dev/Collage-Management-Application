package com.sbz.collageapplication.model

data class NavItem(
    val title: String,
    val icon: Int
)

data class BottomNavItem(
    val title: String,
    val icon: Int,
    val routes: String
)
