package com.app.tmdb_compose.data.repository

import com.app.tmdb_compose.data.ApiService
import com.app.tmdb_compose.data.mapper.DataMappers
import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.domain.repository.ContentRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ContentRepository {
    private val sampleMoviesForCategories = List(10) {
        ContentItem(
            id = "cat_id_$it",
            title = "Category Movie ${it + 1}",
            description = "Desc category ${it + 1}",
            imageUrl = "[https://placehold.co/300x450/7B1FA2/FFFFFF?text=Category+$](https://placehold.co/300x450/7B1FA2/FFFFFF?text=Category+$){it+1}&font=roboto",
            voteAverage = 7.0 + (it * 0.1)
        )
    }

    override suspend fun getFeaturedContent(): Result<List<ContentItem>> {
        return try {
            val response = apiService.getPopularMovies()
            if (response.isSuccessful) {
                Result.success(DataMappers.tmdbMovieListResponseToContentItemList(response.body()))
            } else {
                Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCategorizedContent(categoryTMDBPath: String): Result<List<ContentItem>> {
        val tmdbCategoryPath = when (categoryTMDBPath.lowercase().replace(" ", "_")) {
            "now_playing" -> "now_playing"; "upcoming" -> "upcoming"; "top_rated" -> "top_rated"; "popular" -> "popular"; else -> "popular"
        }
        return try {
            val response =
                if (tmdbCategoryPath == "popular") apiService.getPopularMovies(sortBy = "popularity.desc") else apiService.getMoviesByCategory(
                    categoryPath = tmdbCategoryPath
                )
            if (response.isSuccessful) {
                Result.success(DataMappers.tmdbMovieListResponseToContentItemList(response.body()))
            } else {
                Result.failure(Exception("API Error (Category: $categoryTMDBPath): ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchContent(query: String): Result<List<ContentItem>> =
        Result.success(sampleMoviesForCategories.filter {
            it.title.contains(
                query,
                ignoreCase = true
            )
        })

    override suspend fun getMyListContent(): Result<List<ContentItem>> =
        Result.success(sampleMoviesForCategories.take(4))
}