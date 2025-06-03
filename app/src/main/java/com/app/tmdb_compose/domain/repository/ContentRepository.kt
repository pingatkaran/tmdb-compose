package com.app.tmdb_compose.domain.repository

import com.app.tmdb_compose.domain.model.ContentItem

interface ContentRepository {
    suspend fun getHomeContent(): Result<List<ContentItem>>
    suspend fun searchContent(query: String): Result<List<ContentItem>>
    suspend fun getMyListContent(): Result<List<ContentItem>>
    // Add methods for Room (e.g., saveItem, deleteItem, getSavedItems)
    // Add methods for Retrofit (e.g., fetchRemoteData)
}