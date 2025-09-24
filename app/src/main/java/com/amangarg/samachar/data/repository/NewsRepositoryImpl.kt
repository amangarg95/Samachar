package com.amangarg.samachar.data.repository

import com.amangarg.samachar.data.mappers.toDomain
import com.amangarg.samachar.data.remote.service.NetworkService
import com.amangarg.samachar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : NewsRepository {

    override fun searchNews(
        query: String,
        pageNum: Int,
        pageSize: Int
    ) = flow {
        emit(
            networkService.searchNews(
                searchQuery = query
            ).articles.map {
                it.toDomain()
            }
        )
    }

    override fun getTopHeadlinesByCountry(
        country: String,
        pageNum: Int,
        pageSize: Int
    ) = flow {
        emit(
            networkService.getTopHeadlinesByCountry(country).articles.map {
                it.toDomain()
            }
        )
    }

    override fun getTopHeadlinesBySource(
        source: String,
        pageNum: Int,
        pageSize: Int
    ) = flow {
        emit(
            networkService.getTopHeadlinesBySource(source).articles.map {
                it.toDomain()
            }
        )
    }

    override fun getTopHeadlinesByLanguage(
        language: String,
        pageNum: Int,
        pageSize: Int
    ) = flow {
        emit(
            networkService.getTopHeadlinesByLanguage(language).articles.map {
                it.toDomain()
            }
        )
    }

    override fun getSources() = flow {
        emit(
            networkService.getSources().sources.map {
                it.toDomain()
            }
        )
    }
}