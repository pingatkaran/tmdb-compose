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
fun MainApp() {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        Screen.Home,
        Screen.Search,
        Screen.MyList
    )

    Scaffold(
        bottomBar = {
            BottomAppBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                // Inject HomeViewModel using Hilt
                val homeViewModel: HomeViewModel = hiltViewModel()
                HomeScreen(viewModel = homeViewModel)
            }
            composable(Screen.Search.route) {
                // Inject SearchViewModel using Hilt
                val searchViewModel: SearchViewModel = hiltViewModel()
                SearchScreen(viewModel = searchViewModel)
            }
            composable(Screen.MyList.route) {
                // Inject MyListViewModel using Hilt
                val myListViewModel: MyListViewModel = hiltViewModel()
                MyListScreen(viewModel = myListViewModel)
            }
        }
    }
}