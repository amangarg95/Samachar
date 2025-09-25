package com.amangarg.samachar.domain.repository

import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.domain.model.Source
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun searchNews(
        query: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<List<Article>>

    suspend fun getTopHeadlinesByCountry(
        country: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<List<Article>>

    suspend fun getTopHeadlinesBySource(
        source: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<List<Article>>

    suspend fun getTopHeadlinesByLanguage(
        language: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<List<Article>>

    suspend fun getSources(): Flow<List<Source>>
    suspend fun getCachedArticles(): Flow<List<Article>>
    suspend fun getBookmarkedArticles(): Flow<List<Article>>
    suspend fun bookmarkArticle(article: Article)
    suspend fun unbookmarkArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    suspend fun clearCache()
    suspend fun getBookmarkedCount(): Int
    suspend fun getCachedCount(): Int
}