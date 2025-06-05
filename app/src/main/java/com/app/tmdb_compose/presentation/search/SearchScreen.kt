package com.app.tmdb_compose.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.tmdb_compose.R
import androidx.compose.ui.res.painterResource as painterResource1

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    val uiState by viewModel.uiState.collectAsState();
    var query by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it; viewModel.search(it) },
            label = { Text("Search movies, tv shows...") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Filled.Search, "Search") },
            singleLine = true
        )
        Spacer(Modifier.height(16.dp))
        when (val state = uiState) {
            is OriginalSearchUiState.Loading -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }

            is OriginalSearchUiState.Success -> {/*
                if (state.data.isEmpty() && query.isNotEmpty() && !(uiState.value is OriginalSearchUiState.Loading)) {
                    Text(
                        "No results found for '$query'",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }; LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(
                        state.data,
                        key = { it.id }) { item ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.padding(
                                    8.dp
                                ), verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = item.imageUrl,
                                    contentDescription = item.title,
                                    modifier = Modifier
                                        .size(60.dp, 90.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop,
                                    placeholder = painterResource1(id = R.drawable.ic_launcher_background),
                                    error = painterResource1(id = R.drawable.ic_launcher_background)
                                ); Spacer(Modifier.width(8.dp)); Column {
                                Text(
                                    item.title,
                                    style = MaterialTheme.typography.titleMedium
                                ); Text(
                                item.description.take(80) + "...",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                            }
                            }
                        }
                    }
                }
           */ }

            is OriginalSearchUiState.Error -> Text(
                "Error: ${state.message}",
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}