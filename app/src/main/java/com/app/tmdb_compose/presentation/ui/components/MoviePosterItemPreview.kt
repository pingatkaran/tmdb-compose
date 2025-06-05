package com.app.tmdb_compose.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.presentation.ui.theme.TmdbcomposeTheme

@Preview(showBackground = true, name = "Movie Poster Item Preview")
@Composable
fun MoviePosterItemPreview() {
    TmdbcomposeTheme {
        MoviePosterItem(
            movie = ContentItem("1", "Sample Movie", "This is a great sample movie.", imageUrl = "https://placehold.co/200x300"),
            onClick = {}
        )
    }
}