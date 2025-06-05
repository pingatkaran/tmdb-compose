package com.app.tmdb_compose.domain.usecase

import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.domain.repository.ContentRepository
import javax.inject.Inject

class GetContentUseCase @Inject constructor(private val repository: ContentRepository) {
    suspend fun executeForFeatured(): Result<List<ContentItem>> = repository.getFeaturedContent()
    suspend fun executeForCategory(category: String): Result<List<ContentItem>> = repository.getCategorizedContent(category)
}