package com.amangarg.samachar.data.repository

import com.amangarg.samachar.common.util.ResultWrapper
import com.amangarg.samachar.common.util.safeApiCall
import com.amangarg.samachar.common.util.toDomain
import com.amangarg.samachar.common.util.toEntity
import com.amangarg.samachar.data.local.database.DatabaseService
import com.amangarg.samachar.data.mappers.toDomain
import com.amangarg.samachar.data.remote.service.NetworkService
import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.domain.model.Source
import com.amangarg.samachar.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) : NewsRepository {

    override suspend fun searchNews(
        query: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<ResultWrapper<List<Article>>> = flow<ResultWrapper<List<Article>>> {
        val result = safeApiCall {
            networkService.searchNews(searchQuery = query).articles.map { it.toDomain() }
        }
        when (result) {
            is ResultWrapper.Success -> {
                val articleEntities = result.value.map { it.toEntity().copy(isCached = true) }
                databaseService.cacheArticles(articleEntities)
                emit(ResultWrapper.Success(result.value))
            }
            is ResultWrapper.NetworkError, is ResultWrapper.GenericError -> {
                val cachedArticles = databaseService.getCachedArticles()
                    .map { entities -> entities.map { it.toDomain() } }
                    .first()
                emit(ResultWrapper.Success(cachedArticles))
            }
        }
    }.catch { e ->
        emit(ResultWrapper.GenericError(null, e.localizedMessage))
    }.flowOn(Dispatchers.IO)

    override suspend fun getTopHeadlinesByCountry(
        country: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<List<Article>> = flow {
        try {
            val networkArticles = networkService.getTopHeadlinesByCountry(country)
                .articles
                .map { it.toDomain() }
            val articleEntities = networkArticles.map {
                it.toEntity().copy(isCached = true)
            }
            databaseService.refreshArticleCache(articleEntities)
            emit(networkArticles)
        } catch (e: Exception) {
            val cachedArticles = databaseService.getCachedArticles()
                .map { articles -> articles.map { it.toDomain() } }
            emit(cachedArticles.first())
        }
    }

    override suspend fun getTopHeadlinesBySource(
        source: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<List<Article>> = flow {
        try {
            val networkArticles = networkService.getTopHeadlinesBySource(source)
                .articles
                .map { it.toDomain() }

            val articleEntities = networkArticles.map {
                it.toEntity().copy(isCached = true)
            }
            databaseService.cacheArticles(articleEntities)

            emit(networkArticles)
        } catch (e: Exception) {
            val cachedArticles = databaseService.getCachedArticles()
                .map { articles ->
                    articles.map { it.toDomain() }.filter { it.source?.name == source }
                }
            emit(cachedArticles.first())

        }
    }

    override suspend fun getTopHeadlinesByLanguage(
        language: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<List<Article>> = flow {
        try {
            val networkArticles = networkService.getTopHeadlinesByLanguage(language)
                .articles
                .map { it.toDomain() }

            val articleEntities = networkArticles.map {
                it.toEntity().copy(isCached = true)
            }
            databaseService.cacheArticles(articleEntities)

            emit(networkArticles)
        } catch (e: Exception) {
            val cachedArticles = databaseService.getCachedArticles()
                .map { articles ->
                    articles.map { it.toDomain() }
                }
            emit(cachedArticles.first())

        }
    }

    override suspend fun getSources(): Flow<List<Source>> = flow {
        try {
            val sources = networkService.getSources().sources.map { it.toDomain() }
            emit(sources)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    override suspend fun getCachedArticles(): Flow<List<Article>> =
        databaseService.getCachedArticles().map { articles ->
            articles.map { it.toDomain() }
        }

    override suspend fun getBookmarkedArticles(): Flow<List<Article>> =
        databaseService.getBookmarkedArticles().map { articles ->
            articles.map { it.toDomain() }
        }

    override suspend fun bookmarkArticle(article: Article) {
        val articleEntity = article.toEntity().copy(isBookmarked = true)
        databaseService.saveArticle(articleEntity)
    }

    override suspend fun unbookmarkArticle(article: Article) {
        val articleEntity = article.toEntity().copy(isBookmarked = false)
        databaseService.updateArticle(articleEntity)
    }

    override suspend fun deleteArticle(article: Article) {
        databaseService.deleteArticle(article.toEntity())
    }

    override suspend fun clearCache() {
        databaseService.clearCache()
    }

    override suspend fun getBookmarkedCount(): Int =
        databaseService.getBookmarkedCount()

    override suspend fun getCachedCount(): Int =
        databaseService.getCachedCount()
}


