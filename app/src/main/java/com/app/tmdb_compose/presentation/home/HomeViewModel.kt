package com.app.tmdb_compose.presentation.home

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
class HomeViewModel @Inject constructor(
    private val getContentUseCase: GetContentUseCase // Injected UseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<ContentItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ContentItem>>> = _uiState

    init {
        loadHomeContent()
    }

    fun loadHomeContent() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getContentUseCase.executeForHome()
                .onSuccess { items -> _uiState.value = UiState.Success(items) }
                .onFailure { error -> _uiState.value = UiState.Error(error.message ?: "Unknown error") }
        }
    }
}