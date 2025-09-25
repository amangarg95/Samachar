package com.amangarg.samachar.di.module

import com.amangarg.samachar.data.local.database.DatabaseService
import com.amangarg.samachar.data.remote.service.NetworkService
import com.amangarg.samachar.data.repository.NewsRepositoryImpl
import com.amangarg.samachar.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        networkService: NetworkService,
        databaseService: DatabaseService
    ): NewsRepository {
        return NewsRepositoryImpl(
            networkService = networkService,
            databaseService = databaseService
        )
    }
}
