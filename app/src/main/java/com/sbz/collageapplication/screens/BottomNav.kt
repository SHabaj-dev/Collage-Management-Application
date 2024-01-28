package com.sbz.collageapplication.screens

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sbz.collageapplication.R
import com.sbz.collageapplication.model.BottomNavItem
import com.sbz.collageapplication.model.NavItem
import com.sbz.collageapplication.navigation.Routes
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(navController: NavHostController) {

    val bottomNavController = rememberNavController()

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val context = LocalContext.current

    val list = listOf(
        NavItem(
            title = "Website",
            icon = R.drawable.ic_website
        ),
        NavItem(
            title = "Notice",
            icon = R.drawable.ic_notice
        ),

        NavItem(
            title = "Notes",
            icon = R.drawable.ic_notes
        ),
        NavItem(
            title = "Contact Us",
            icon = R.drawable.ic_contact_us
        )
    )

    ModalNavigationDrawer(drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {

                list.forEachIndexed { index, items ->
                    NavigationDrawerItem(label = {
                        Text(text = items.title)
                    }, selected = index == selectedItemIndex,
                        onClick = {
                            Toast.makeText(context, items.title, Toast.LENGTH_SHORT).show()
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = items.icon),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        })
                }

            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Collage App")
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch { drawerState.open() }
                                }
                            ) {
                                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                            }
                        }
                    )
                },
                bottomBar = { MyBottomNav(navController = bottomNavController) }
            ) { paddingValues ->
                NavHost(
                    navController = bottomNavController,
                    startDestination = Routes.Home.route,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable(route = Routes.Home.route) {
                        Home()
                    }

                    composable(route = Routes.Faculty.route) {
                        Faculty()
                    }

                    composable(route = Routes.Gallery.route) {
                        Gallery()
                    }

                    composable(route = Routes.AboutUs.route) {
                        AboutUs()
                    }
                }
            }
        })

}

@Composable
fun MyBottomNav(navController: NavController) {

    val backStackEntry = navController.currentBackStackEntryAsState()


    val list = listOf(
        BottomNavItem(
            title = "Home",
            icon = R.drawable.ic_home,
            routes = Routes.Home.route
        ),
        BottomNavItem(
            title = "Faculty",
            icon = R.drawable.ic_faculty,
            routes = Routes.Faculty.route
        ),
        BottomNavItem(
            title = "Gallery",
            icon = R.drawable.ic_gallery,
            routes = Routes.Gallery.route
        ),
        BottomNavItem(
            title = "About Us",
            icon = R.drawable.ic_about_us,
            routes = Routes.AboutUs.route
        )
    )

    BottomAppBar {
        list.forEach {
            val currentRoute = it.routes
            val otherRoutes =
                try {
                    backStackEntry.value!!.destination.route
                } catch (e: Exception) {
                    Routes.Home.route
                }

            val selected = currentRoute == otherRoutes

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(it.routes) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon), contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                })
        }
    }

}