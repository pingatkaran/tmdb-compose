package com.app.tmdb_compose.di

import com.app.tmdb_compose.data.repository.ContentRepositoryImpl
import com.app.tmdb_compose.domain.repository.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Defines the scope of the bindings
object AppModule {

    // This is where you'd provide your Retrofit instance, Room database, etc.
    // For example:
    // @Provides
    // @Singleton
    // fun provideApiService(): ApiService {
    //     return Retrofit.Builder()
    //         .baseUrl("https://api.example.com/")
    //         .addConverterFactory(GsonConverterFactory.create())
    //         .build()
    //         .create(ApiService::class.java)
    // }

    // @Provides
    // @Singleton
    // fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
    //     return Room.databaseBuilder(
    //         appContext,
    //         AppDatabase::class.java,
    //         "app_database_name"
    //     ).build()
    // }

    // @Provides
    // @Singleton
    // fun provideContentDao(appDatabase: AppDatabase): ContentDao {
    //    return appDatabase.contentDao()
    // }

    @Provides
    @Singleton
    fun provideContentRepository(
        // localDataSource: RoomDataSource, // These would be actual dependencies
        // remoteDataSource: ApiService
    ): ContentRepository {
        // For now, returning the placeholder implementation
        return ContentRepositoryImpl(/*localDataSource, remoteDataSource*/)
    }

    // Use cases are typically provided directly if their constructor is simple
    // or via a @Provides method if they have complex dependencies not handled by Hilt.
    // Since GetContentUseCase has ContentRepository injected, Hilt can provide it.
}

