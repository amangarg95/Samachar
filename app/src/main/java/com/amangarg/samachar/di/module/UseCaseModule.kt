package com.amangarg.samachar.di.module

import com.amangarg.samachar.domain.repository.NewsRepository
import com.amangarg.samachar.domain.usecase.GetFormattedCurrentDateUseCase
import com.amangarg.samachar.domain.usecase.GetSourcesUseCase
import com.amangarg.samachar.domain.usecase.GetTopHeadlinesByCountryUseCase
import com.amangarg.samachar.domain.usecase.GetTopHeadlinesByLanguageUseCase
import com.amangarg.samachar.domain.usecase.GetTopHeadlinesBySourceUseCase
import com.amangarg.samachar.domain.usecase.SearchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(
        repository: NewsRepository
    ): SearchNewsUseCase = SearchNewsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetTopHeadlinesByLanguageUseCase(
        repository: NewsRepository
    ): GetTopHeadlinesByLanguageUseCase = GetTopHeadlinesByLanguageUseCase(repository)

    @Provides
    @Singleton
    fun provideGetTopHeadlinesByCountryUseCase(
        repository: NewsRepository
    ): GetTopHeadlinesByCountryUseCase = GetTopHeadlinesByCountryUseCase(repository)

    @Provides
    @Singleton
    fun provideGetTopHeadlinesBySourceUseCase(
        repository: NewsRepository
    ): GetTopHeadlinesBySourceUseCase = GetTopHeadlinesBySourceUseCase(repository)

    @Provides
    @Singleton
    fun provideGetSourcesUseCase(
        repository: NewsRepository
    ): GetSourcesUseCase = GetSourcesUseCase(repository)

    @Provides
    @Singleton
    fun provideGetFormattedCurrentDateUseCase() = GetFormattedCurrentDateUseCase()
}