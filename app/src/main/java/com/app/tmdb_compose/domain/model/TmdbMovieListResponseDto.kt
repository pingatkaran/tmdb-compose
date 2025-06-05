package com.app.tmdb_compose.domain.model

import com.google.gson.annotations.SerializedName

data class TmdbMovieListResponseDto(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<TmdbMovieResultDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)