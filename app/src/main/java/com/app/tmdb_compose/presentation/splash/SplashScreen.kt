package com.app.tmdb_compose.presentation.splash

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.tmdb_compose.R
import com.app.tmdb_compose.presentation.navigation.Screen
import com.app.tmdb_compose.presentation.ui.theme.CustomAppPrimary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Use LaunchedEffect for side effects that run once when the composable enters the composition
    LaunchedEffect(key1 = true) {
        delay(3000L) // Delay for 3 seconds
        navController.navigate(Screen.Home.route) {
            // Pop up to the splash route and clear it from the back stack
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(CustomAppPrimary),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(150.dp)
        )
    }
}
