package com.app.tmdb_compose.data

import com.app.tmdb_compose.domain.model.TmdbMovieListResponseDto
import com.app.tmdb_compose.domain.model.TmdbMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Response<TmdbMovieResponse> // Using Retrofit's Response wrapper

    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Response<TmdbMovieListResponseDto> // Using Response wrapper for error handling

    // Add other endpoints here, e.g., for categories
    @GET("movie/{category}") // Example: category could be "now_playing", "upcoming", etc.
    suspend fun getMoviesByCategory(
        @retrofit2.http.Path("category") categoryPath: String, // e.g. "now_playing", "upcoming"
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<TmdbMovieListResponseDto>

    // Add other endpoints here, e.g., for categories, search
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int = 1): Response<TmdbMovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int = 1): Response<TmdbMovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int = 1): Response<TmdbMovieResponse>

//    @GET("movie/popular") // This is similar to discover but could be used for "Popular" tab
//    suspend fun getPopularMovies(@Query("page") page: Int = 1): Response<TmdbMovieResponse>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): Response<TmdbMovieResponse>
}