package com.app.tmdb_compose.data.mapper

import com.app.tmdb_compose.di.ApiConstants
import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.domain.model.MovieResultDto

fun MovieResultDto.toContentItem(): ContentItem {
    return ContentItem(
        id = this.id.toString(),
        title = this.title,
        description = this.overview,
        imageUrl = this.posterPath?.let { ApiConstants.IMAGE_BASE_URL + ApiConstants.POSTER_SIZE_W500 + it }
    )
}