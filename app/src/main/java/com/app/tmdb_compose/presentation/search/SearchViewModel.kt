package com.app.tmdb_compose.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.domain.usecase.GetContentUseCase
import com.app.tmdb_compose.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getContentUseCase: GetContentUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<ContentItem>>>(UiState.Success(emptyList())) // Initial empty state
    val uiState: StateFlow<UiState<List<ContentItem>>> = _uiState

    fun search(query: String) {
        if (query.isBlank()) {
            _uiState.value = UiState.Success(emptyList()) // Clear results if query is blank
            return
        }
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getContentUseCase.executeForSearch(query)
                .onSuccess { items -> _uiState.value = UiState.Success(items) }
                .onFailure { error -> _uiState.value = UiState.Error(error.message ?: "Unknown error") }
        }
    }
}

