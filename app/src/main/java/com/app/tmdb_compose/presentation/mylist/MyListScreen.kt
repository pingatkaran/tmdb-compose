package com.app.tmdb_compose.presentation.mylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.tmdb_compose.R
import com.app.tmdb_compose.presentation.search.OriginalSearchUiState

@Composable
fun MyListScreen(viewModel: MyListViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "My Watch List",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            when (val state = uiState) {
                is OriginalSearchUiState.Loading -> CircularProgressIndicator()
                is OriginalSearchUiState.Success -> {
                    if (state.data.isEmpty()) {
                        Text("Your watch list is empty.", textAlign = TextAlign.Center)
                    } else {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(
                                state.data,
                                key = { it.id }) { item ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Nav to details */ }) {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        AsyncImage(
                                            model = item.imageUrl,
                                            contentDescription = item.title,
                                            modifier = Modifier
                                                .size(80.dp, 120.dp)
                                                .clip(RoundedCornerShape(8.dp)),
                                            contentScale = ContentScale.Crop,
                                            placeholder = painterResource(id = R.drawable.ic_launcher_background),
                                            error = painterResource(id = R.drawable.ic_launcher_background)
                                        ); Spacer(Modifier.width(12.dp)); Column(
                                        modifier = Modifier.weight(
                                            1f
                                        )
                                    ) {
                                        Text(
                                            item.title,
                                            style = MaterialTheme.typography.titleLarge
                                        ); Spacer(Modifier.height(4.dp)); Text(
                                        item.description.take(100) + "...",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray
                                    )
                                    }
                                    }
                                }
                            }
                        }
                    }
                }

                is OriginalSearchUiState.Error -> {
                    Text(
                        "Error: ${state.message}",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    ); Spacer(Modifier.height(8.dp)); Button(onClick = { viewModel.loadMyList() }) {
                        Text(
                            "Retry"
                        )
                    }
                }
            }
        }
    }
}