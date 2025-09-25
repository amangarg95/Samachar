package com.amangarg.samachar.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.amangarg.samachar.data.local.database.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Upsert
    suspend fun upsert(article: ArticleEntity)

    @Upsert
    suspend fun upsertAll(articles: List<ArticleEntity>)

    @Update
    suspend fun update(article: ArticleEntity)

    @Delete
    suspend fun delete(article: ArticleEntity)

    @Query("SELECT * FROM articles WHERE is_cached = 1 ORDER BY created_at DESC")
    fun getCachedArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE is_bookmarked = 1 ORDER BY created_at DESC")
    fun getBookmarkedArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles ORDER BY created_at DESC")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Query("UPDATE articles SET is_bookmarked = :isBookmarked WHERE id = :id")
    suspend fun updateBookmarkStatus(id: Int, isBookmarked: Boolean)

    @Query("DELETE FROM articles WHERE is_cached = 1")
    suspend fun clearCache()

    @Query("DELETE FROM articles WHERE is_bookmarked = 0")
    suspend fun clearNonBookmarkedArticles()

    @Transaction
    suspend fun refreshCache(articles: List<ArticleEntity>) {
        clearCache()
        val cachedArticles = articles.map { it.copy(isCached = true, isBookmarked = false) }
        upsertAll(cachedArticles)
    }

    @Query("SELECT COUNT(*) FROM articles WHERE is_bookmarked = 1")
    suspend fun getBookmarkedCount(): Int

    @Query("SELECT COUNT(*) FROM articles WHERE is_cached = 1")
    suspend fun getCachedCount(): Int
}