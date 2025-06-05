package com.app.tmdb_compose.domain.model

import com.google.gson.annotations.SerializedName

data class TmdbMovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieResultDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)