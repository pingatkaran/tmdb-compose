package com.app.tmdb_compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.tmdb_compose.presentation.home.HomeScreen
import com.app.tmdb_compose.presentation.home.HomeViewModel
import com.app.tmdb_compose.presentation.mylist.MyListScreen
import com.app.tmdb_compose.presentation.mylist.MyListViewModel
import com.app.tmdb_compose.presentation.navigation.Screen
import com.app.tmdb_compose.presentation.search.SearchScreen
import com.app.tmdb_compose.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(mainNavController: NavController) { // mainNavController is the one from AppNavigation
    // For bottom navigation, we typically use a new NavController scoped to this Scaffold
    val bottomNavController = rememberNavController()

    val bottomNavItems = listOf(
        Screen.Home,
        Screen.Search,
        Screen.MyList
    ).filter { it.label != null && it.icon != null }

    Scaffold(
        bottomBar = {
            // Only show BottomAppBar if not on the splash screen.
            // This check is implicitly handled by having MainApp separate from SplashScreen's NavHost entry.
            BottomAppBar {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            bottomNavController.navigate(screen.route) {
                                popUpTo(bottomNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(screen.icon!!, contentDescription = screen.label!!) },
                        label = { Text(screen.label!!) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // This NavHost is for the content within the Bottom Navigation structure
        NavHost(
            navController = bottomNavController, // Use the new NavController for bottom bar navigation
            startDestination = Screen.Home.route, // Default screen for bottom navigation
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                val homeViewModel: HomeViewModel = hiltViewModel()
                HomeScreen(viewModel = homeViewModel)
            }
            composable(Screen.Search.route) {
                val searchViewModel: SearchViewModel = hiltViewModel()
                SearchScreen(viewModel = searchViewModel)
            }
            composable(Screen.MyList.route) {
                val myListViewModel: MyListViewModel = hiltViewModel()
                MyListScreen(viewModel = myListViewModel)
            }
        }
    }
}