package com.app.tmdb_compose.presentation.search

sealed interface OriginalSearchUiState<out T> { // Renamed to avoid conflict
    object Loading : OriginalSearchUiState<Nothing>
    data class Success<T>(val data: T) : OriginalSearchUiState<T>
    data class Error(val message: String) : OriginalSearchUiState<Nothing>
}