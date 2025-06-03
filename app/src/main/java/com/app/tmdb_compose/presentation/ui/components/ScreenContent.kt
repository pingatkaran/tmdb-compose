package com.app.tmdb_compose.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.presentation.common.UiState

@Composable
fun ScreenContent(
    name: String,
    uiState: UiState<List<ContentItem>>,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = name, style = MaterialTheme.typography.headlineMedium)
            when (uiState) {
                is UiState.Loading -> Text("Loading...")
                is UiState.Success -> {
                    if (uiState.data.isEmpty()) {
                        Text("No content found.")
                    } else {
                        uiState.data.forEach { item ->
                            Text(
                                "${item.title}: ${item.description}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

                is UiState.Error -> {
                    Text("Error: ${uiState.message}", style = MaterialTheme.typography.bodyLarge)
                    // Button(onClick = onRetry) { Text("Retry") } // Example retry button
                }
            }
        }
    }
}
