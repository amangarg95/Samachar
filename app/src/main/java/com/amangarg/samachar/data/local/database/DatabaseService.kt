package com.amangarg.samachar.data.local.database

import com.amangarg.samachar.data.local.database.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseService {

    // Article Management
    suspend fun saveArticle(article: ArticleEntity)
    suspend fun updateArticle(article: ArticleEntity)
    suspend fun deleteArticle(article: ArticleEntity)

    // Bookmark Operations
    suspend fun bookmarkArticle(articleId: Int)
    suspend fun unbookmarkArticle(articleId: Int)
    fun getBookmarkedArticles(): Flow<List<ArticleEntity>>
    suspend fun getBookmarkedCount(): Int

    // Cache Operations
    suspend fun cacheArticles(articles: List<ArticleEntity>)
    fun getCachedArticles(): Flow<List<ArticleEntity>>
    suspend fun clearCache()
    suspend fun getCachedCount(): Int

    // General Operations
    fun getAllArticles(): Flow<List<ArticleEntity>>
    suspend fun refreshArticleCache(articles: List<ArticleEntity>)
}