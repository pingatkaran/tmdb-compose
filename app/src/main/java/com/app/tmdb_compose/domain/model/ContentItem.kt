package com.app.tmdb_compose.domain.model

data class ContentItem(
    val id: String, // Will use TMDB movie ID (Int) as String
    val title: String,
    val description: String, // Will use TMDB overview
    val imageUrl: String? = null, // Full image URL
    val type: String = "movie",
    val voteAverage: Double = 0.0 // Example: TMDB vote_average
)