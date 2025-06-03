package com.app.tmdb_compose.domain.usecase

import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.domain.repository.ContentRepository
import javax.inject.Inject

class GetContentUseCase @Inject constructor(private val repository: ContentRepository) {
    suspend fun executeForHome(): Result<List<ContentItem>> = repository.getHomeContent()
    suspend fun executeForSearch(query: String): Result<List<ContentItem>> = repository.searchContent(query)
    // ... other use case methods
}