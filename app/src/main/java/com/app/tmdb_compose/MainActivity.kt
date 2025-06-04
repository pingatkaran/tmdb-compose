package com.app.tmdb_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.tmdb_compose.presentation.navigation.Screen
import com.app.tmdb_compose.presentation.splash.SplashScreen
import com.app.tmdb_compose.presentation.ui.theme.TmdbcomposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Marks Activity for Hilt injection
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbcomposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.Splash.route) {
            composable(Screen.Splash.route) {
                SplashScreen(navController = navController)
            }
            composable(Screen.Home.route) { // This route now acts as a gateway to MainApp structure
                MainApp(mainNavController = navController) // Pass the same NavController
            }
            // Define other main app routes here if they need to be directly accessible
            // and not part of the MainApp's internal NavHost for bottom navigation.
            // However, for a typical bottom nav app, MainApp will handle its own navigation.
        }
    }
}