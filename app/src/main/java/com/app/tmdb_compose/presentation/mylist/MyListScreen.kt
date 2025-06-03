package com.app.tmdb_compose.presentation.mylist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.app.tmdb_compose.presentation.ui.components.ScreenContent

@Composable
fun MyListScreen(viewModel: MyListViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    ScreenContent(name = "My List Screen", uiState = uiState) {
        viewModel.loadMyList()
    }
}