package com.app.tmdb_compose.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.app.tmdb_compose.presentation.ui.components.ScreenContent

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    ScreenContent(name = "Home Screen", uiState = uiState) {
        viewModel.loadHomeContent() // Example: retry action
    }
}