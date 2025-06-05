package com.app.tmdb_compose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tmdb_compose.domain.usecase.GetContentUseCase
import com.app.tmdb_compose.presentation.ui.components.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getContentUseCase: GetContentUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    init { loadInitialHomeData() }

    private fun loadInitialHomeData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, screenError = null)
            getContentUseCase.executeForFeatured()
                .onSuccess { featured -> _uiState.value = _uiState.value.copy(featuredMovies = featured) }
                .onFailure { error -> _uiState.value = _uiState.value.copy(screenError = error.localizedMessage ?: "Failed to load featured movies.") }
            loadContentForTab(_uiState.value.selectedTabIndex, true)
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    fun onTabSelected(tabIndex: Int) {
        if (_uiState.value.selectedTabIndex == tabIndex && _uiState.value.moviesForSelectedTab.isNotEmpty() && !_uiState.value.isLoadingTabContent) return
        _uiState.value = _uiState.value.copy(selectedTabIndex = tabIndex)
        loadContentForTab(tabIndex)
    }

    private fun loadContentForTab(tabIndex: Int, isInitialLoad: Boolean = false) {
        viewModelScope.launch {
            if (!isInitialLoad) { _uiState.value = _uiState.value.copy(isLoadingTabContent = true, moviesForSelectedTab = emptyList()) }
            _uiState.value = _uiState.value.copy(screenError = null)
            val category = _uiState.value.tabs.getOrElse(tabIndex) { "Popular" } // Default to popular if index is out of bounds
            getContentUseCase.executeForCategory(category)
                .onSuccess { movies -> _uiState.value = _uiState.value.copy(moviesForSelectedTab = movies) }
                .onFailure { error -> _uiState.value = _uiState.value.copy(screenError = error.localizedMessage ?: "Failed to load movies for $category.") }
            _uiState.value = _uiState.value.copy(isLoadingTabContent = false)
        }
    }
    fun refreshAllData() { loadInitialHomeData() }
    fun refreshTabData() { loadContentForTab(_uiState.value.selectedTabIndex) }
}