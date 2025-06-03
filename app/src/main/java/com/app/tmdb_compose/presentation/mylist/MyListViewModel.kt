package com.app.tmdb_compose.presentation.mylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.domain.repository.ContentRepository
import com.app.tmdb_compose.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val repository: ContentRepository // Example: directly using repository for simplicity here
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<ContentItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ContentItem>>> = _uiState

    init {
        loadMyList()
    }

    fun loadMyList() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getMyListContent() // Using repository directly for this example
                .onSuccess { items -> _uiState.value = UiState.Success(items) }
                .onFailure { error -> _uiState.value = UiState.Error(error.message ?: "Unknown error") }
        }
    }
}