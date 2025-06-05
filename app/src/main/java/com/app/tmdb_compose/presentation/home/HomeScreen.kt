package com.app.tmdb_compose.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.tmdb_compose.presentation.navigation.Screen
import com.app.tmdb_compose.presentation.ui.components.CustomSearchBar
import com.app.tmdb_compose.presentation.ui.components.MoviePosterItem
import com.app.tmdb_compose.presentation.ui.components.SectionTitle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, appNavController: NavController) {
    val uiState by viewModel.uiState.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (uiState.isLoading && uiState.featuredMovies.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary
                        ); Spacer(modifier = Modifier.height(8.dp)); Text("Loading Your Awesome Movies...")
                    }
                }
            }
            return@LazyColumn
        }
        if (uiState.screenError != null && uiState.featuredMovies.isEmpty() && !uiState.isLoading) {
            item {
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "Oops! ${uiState.screenError}",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        ); Spacer(Modifier.height(8.dp)); Button(onClick = { viewModel.refreshAllData() }) {
                        Text(
                            "Retry"
                        )
                    }
                    }
                }
            }
            return@LazyColumn
        }
        item { SectionTitle(title = uiState.greeting) }
        item {
            CustomSearchBar(
                onSearchChanged = { },
                onSearchClicked = { appNavController.navigate(Screen.Search.route) })
        }

        if (uiState.featuredMovies.isNotEmpty()) {
            item {
                LazyRow(contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp)) {
                    items(
                        uiState.featuredMovies,
                        key = { it.id }) { movie ->
                        MoviePosterItem(
                            movie = movie,
                            modifier = Modifier.width(280.dp),
                            onClick = { /* appNavController.navigate(Screen.MovieDetails.createRoute(movie.id)) */ })
                    }
                }
            }
        } else if (!uiState.isLoading) {
            item {
                Text(
                    "No featured movies available.",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        stickyHeader {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 4.dp,
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
            ) {
                TabRow(selectedTabIndex = uiState.selectedTabIndex,
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary,
                    indicator = { tabPositions ->
                        if (uiState.selectedTabIndex < tabPositions.size) {
                            androidx.compose.material3.TabRowDefaults.Indicator(
                                Modifier.tabIndicatorOffset(
                                    tabPositions[uiState.selectedTabIndex]
                                ), height = 3.dp, color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                ) {
                    uiState.tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = uiState.selectedTabIndex == index,
                            onClick = { viewModel.onTabSelected(index) },
                            text = {
                                Text(
                                    title,
                                    color = if (uiState.selectedTabIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            })
                    }
                }
            }
        }
        when {
            uiState.isLoadingTabContent -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            ); Spacer(modifier = Modifier.height(8.dp)); Text(
                            "Loading ${
                                uiState.tabs.getOrNull(
                                    uiState.selectedTabIndex
                                ) ?: "movies"
                            }..."
                        )
                        }
                    }
                }
            }

            uiState.screenError != null && uiState.moviesForSelectedTab.isEmpty() && !uiState.isLoadingTabContent -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                "Oops! ${uiState.screenError} for ${
                                    uiState.tabs.getOrNull(
                                        uiState.selectedTabIndex
                                    ) ?: "this category"
                                }",
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            ); Spacer(Modifier.height(8.dp)); Button(onClick = { viewModel.refreshTabData() }) {
                            Text(
                                "Retry"
                            )
                        }
                        }
                    }
                }
            }

            uiState.moviesForSelectedTab.isNotEmpty() -> {
                val moviesInPairs = uiState.moviesForSelectedTab.chunked(2)
                items(
                    moviesInPairs.size,
                    key = { rowIndex -> "row_${uiState.selectedTabIndex}_$rowIndex" }) { rowIndex -> // Make key more unique per tab
                    val rowItems = moviesInPairs[rowIndex]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        rowItems.forEach { movie ->
                            MoviePosterItem(
                                movie = movie,
                                modifier = Modifier.weight(1f),
                                onClick = { /* appNavController.navigate(Screen.MovieDetails.createRoute(movie.id)) */ })
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier
                                .weight(1f)
                                .padding(6.dp))
                        }
                    }
                    if (rowIndex == moviesInPairs.size - 1) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            else -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "No movies found for ${uiState.tabs.getOrNull(uiState.selectedTabIndex) ?: "this category"}.",
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}