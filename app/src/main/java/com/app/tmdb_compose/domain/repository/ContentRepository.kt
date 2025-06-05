package com.app.tmdb_compose.domain.repository

import com.app.tmdb_compose.domain.model.ContentItem

interface ContentRepository {
    suspend fun getFeaturedContent(): Result<List<ContentItem>>
    suspend fun getCategorizedContent(category: String): Result<List<ContentItem>>
    suspend fun searchContent(query: String): Result<List<ContentItem>>
    suspend fun getMyListContent(): Result<List<ContentItem>>
}