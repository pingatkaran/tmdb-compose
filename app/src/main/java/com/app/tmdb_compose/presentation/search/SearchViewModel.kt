package com.app.tmdb_compose.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.domain.repository.ContentRepository
import com.app.tmdb_compose.domain.usecase.GetContentUseCase
import com.app.tmdb_compose.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: ContentRepository) : ViewModel() { // Uses ContentRepository
    private val _uiState = MutableStateFlow<OriginalSearchUiState<List<ContentItem>>>(OriginalSearchUiState.Success(emptyList()))
    val uiState: StateFlow<OriginalSearchUiState<List<ContentItem>>> = _uiState
    fun search(query: String) {
        if (query.isBlank()) {
            _uiState.value = OriginalSearchUiState.Success(emptyList()); return
        }
        viewModelScope.launch {
            _uiState.value = OriginalSearchUiState.Loading
            repository.searchContent(query) // Using repository directly
                .onSuccess { items -> _uiState.value = OriginalSearchUiState.Success(items) }
                .onFailure { error -> _uiState.value = OriginalSearchUiState.Error(error.message ?: "Unknown error") }
        }
    }
}
