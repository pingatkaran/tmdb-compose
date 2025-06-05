package com.app.tmdb_compose.presentation.ui.components

import com.app.tmdb_compose.domain.model.ContentItem

data class HomeUiState(
    val isLoading: Boolean = true,
    val screenError: String? = null,
    val greeting: String = "What do you want to watch?",
    val featuredMovies: List<ContentItem> = emptyList(),
    val tabs: List<String> = listOf("Now playing", "Upcoming", "Top rated", "Popular"),
    val selectedTabIndex: Int = 0,
    val moviesForSelectedTab: List<ContentItem> = emptyList(),
    val isLoadingTabContent: Boolean = false
    
)