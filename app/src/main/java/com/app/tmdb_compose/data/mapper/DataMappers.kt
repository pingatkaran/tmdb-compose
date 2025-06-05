package com.app.tmdb_compose.data.mapper

import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.domain.model.TmdbMovieListResponseDto
import com.app.tmdb_compose.domain.model.TmdbMovieResultDto

object DataMappers {
    private const val TMDB_IMAGE_BASE_URL_W500 = "https://image.tmdb.org/t/p/w500"

    fun tmdbMovieResultToContentItem(dto: TmdbMovieResultDto): ContentItem {
        return ContentItem(
            id = dto.id.toString(),
            title = dto.title,
            description = dto.overview,
            imageUrl = dto.posterPath?.let { "$TMDB_IMAGE_BASE_URL_W500$it" }, // Construct full URL
            voteAverage = dto.voteAverage
        )
    }

    fun tmdbMovieListResponseToContentItemList(responseDto: TmdbMovieListResponseDto?): List<ContentItem> {
        return responseDto?.results?.map { tmdbMovieResultToContentItem(it) } ?: emptyList()
    }
}