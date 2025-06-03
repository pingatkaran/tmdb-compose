package com.app.tmdb_compose.presentation.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.app.tmdb_compose.presentation.ui.components.ScreenContent

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    // You'd typically have a TextField here to input the search query
    // For now, just displaying the state
    ScreenContent(name = "Search Screen", uiState = uiState) {
        // viewModel.search("some query") // Example: retry or initial search
    }
    // Add a TextField and a Button to trigger search:
    // Column {
    //     var text by remember { mutableStateOf("") }
    //     TextField(value = text, onValueChange = { text = it }, label = { Text("Search query") })
    //     Button(onClick = { viewModel.search(text) }) { Text("Search") }
    //     ScreenContent(name = "Search Results", uiState = uiState) {}
    // }
}

