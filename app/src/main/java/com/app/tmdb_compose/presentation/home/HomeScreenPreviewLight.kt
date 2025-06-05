package com.app.tmdb_compose.presentation.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.app.tmdb_compose.data.ApiService
import com.app.tmdb_compose.data.repository.ContentRepositoryImpl
import com.app.tmdb_compose.domain.model.TmdbMovieListResponseDto
import com.app.tmdb_compose.domain.model.TmdbMovieResponse
import com.app.tmdb_compose.domain.model.TmdbMovieResultDto
import com.app.tmdb_compose.domain.usecase.GetContentUseCase
import com.app.tmdb_compose.presentation.ui.theme.TmdbcomposeTheme
import retrofit2.Response

@Preview(showBackground = true, name = "Home Screen Preview (Light)")
@Composable
fun HomeScreenPreviewLight() {
    TmdbcomposeTheme (darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            // For preview, use a dummy ApiService or a ContentRepositoryImpl with placeholder data
            val dummyApiService = object : ApiService {
                override suspend fun discoverMovies(
                    includeAdult: Boolean,
                    includeVideo: Boolean,
                    language: String,
                    page: Int,
                    sortBy: String
                ): Response<TmdbMovieResponse> {
                    TODO("Not yet implemented")
                }

                override suspend fun getPopularMovies(includeAdult: Boolean, includeVideo: Boolean, language: String, page: Int, sortBy: String): Response<TmdbMovieListResponseDto> {
                    val results = List(5) { TmdbMovieResultDto(it, "Featured Movie $it", "Desc $it", "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg", null, 7.5, null) }
                    return Response.success(TmdbMovieListResponseDto(1, results, 1, 5))
                }
                override suspend fun getMoviesByCategory(categoryPath: String, language: String, page: Int): Response<TmdbMovieListResponseDto> {
                    val results = List(10) { TmdbMovieResultDto(it+100, "$categoryPath Movie $it", "Desc $it", "/ bestaan.jpg", null, 7.1, null) }
                    return Response.success(TmdbMovieListResponseDto(1, results, 1, 10))
                }

                override suspend fun getNowPlayingMovies(page: Int): Response<TmdbMovieResponse> {
                    TODO("Not yet implemented")
                }

                override suspend fun getUpcomingMovies(page: Int): Response<TmdbMovieResponse> {
                    TODO("Not yet implemented")
                }

                override suspend fun getTopRatedMovies(page: Int): Response<TmdbMovieResponse> {
                    TODO("Not yet implemented")
                }

                override suspend fun searchMovies(
                    query: String,
                    page: Int
                ): Response<TmdbMovieResponse> {
                    TODO("Not yet implemented")
                }
            }
            val previewViewModel = HomeViewModel(GetContentUseCase(ContentRepositoryImpl(dummyApiService)))
            HomeScreen(viewModel = previewViewModel, appNavController = rememberNavController())
        }
    }
}