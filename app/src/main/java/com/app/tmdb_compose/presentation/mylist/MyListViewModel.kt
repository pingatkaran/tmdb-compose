package com.app.tmdb_compose.presentation.mylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.domain.repository.ContentRepository
import com.app.tmdb_compose.presentation.search.OriginalSearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(private val repository: ContentRepository) : ViewModel() {
    private val _uiState =
        MutableStateFlow<OriginalSearchUiState<List<ContentItem>>>(OriginalSearchUiState.Loading);
    val uiState: StateFlow<OriginalSearchUiState<List<ContentItem>>> = _uiState;

    init {
        loadMyList()
    }

    fun loadMyList() {
        viewModelScope.launch {
            _uiState.value = OriginalSearchUiState.Loading; repository.getMyListContent()
            .onSuccess { _uiState.value = OriginalSearchUiState.Success(it) }
            .onFailure { _uiState.value = OriginalSearchUiState.Error(it.message ?: "Error") }
        }
    }
}