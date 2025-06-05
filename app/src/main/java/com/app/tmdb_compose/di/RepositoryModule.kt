package com.app.tmdb_compose.di

import com.app.tmdb_compose.data.ApiService
import com.app.tmdb_compose.data.repository.ContentRepositoryImpl
import com.app.tmdb_compose.domain.repository.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule { // New module for repository
    @Provides
    @Singleton
    fun provideContentRepository(apiService: ApiService): ContentRepository {
        return ContentRepositoryImpl(apiService)
    }
}