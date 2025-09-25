package com.amangarg.samachar.data.local.database

import com.amangarg.samachar.data.local.database.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SamacharDatabaseService @Inject constructor(
    private val database: SamacharDatabase
) : DatabaseService {

    private val articleDao = database.articleDao()

    override suspend fun saveArticle(article: ArticleEntity) {
        articleDao.upsert(article.copy(updatedAt = System.currentTimeMillis()))
    }

    override suspend fun updateArticle(article: ArticleEntity) {
        articleDao.update(article.copy(updatedAt = System.currentTimeMillis()))
    }

    override suspend fun deleteArticle(article: ArticleEntity) {
        articleDao.delete(article)
    }

    override suspend fun bookmarkArticle(articleId: Int) {
        articleDao.updateBookmarkStatus(articleId, true)
    }

    override suspend fun unbookmarkArticle(articleId: Int) {
        articleDao.updateBookmarkStatus(articleId, false)
    }

    override fun getBookmarkedArticles(): Flow<List<ArticleEntity>> =
        articleDao.getBookmarkedArticles()

    override suspend fun getBookmarkedCount(): Int =
        articleDao.getBookmarkedCount()

    override suspend fun cacheArticles(articles: List<ArticleEntity>) {
        val cachedArticles = articles.map {
            it.copy(
                isCached = true,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        }
        articleDao.upsertAll(cachedArticles)
    }

    override fun getCachedArticles(): Flow<List<ArticleEntity>> =
        articleDao.getCachedArticles()

    override suspend fun clearCache() {
        articleDao.clearCache()
    }

    override suspend fun getCachedCount(): Int =
        articleDao.getCachedCount()

    override fun getAllArticles(): Flow<List<ArticleEntity>> =
        articleDao.getAllArticles()

    override suspend fun refreshArticleCache(articles: List<ArticleEntity>) {
        articleDao.refreshCache(articles)
    }
}