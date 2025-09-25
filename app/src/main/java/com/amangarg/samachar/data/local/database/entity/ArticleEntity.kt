package com.amangarg.samachar.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "articles",
    indices = [Index(
        value = ["title", "url"],
        unique = true
    )]
)
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,

    @Embedded
    val source: SourceEntity,

    @ColumnInfo("author")
    val author: String?,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("description")
    val description: String?,

    @ColumnInfo("url")
    val url: String,

    @ColumnInfo("url_to_image")
    val urlToImage: String?,

    @ColumnInfo("published_at")
    val publishedAt: String?,

    @ColumnInfo("content")
    val content: String?,

    @ColumnInfo("is_bookmarked")
    val isBookmarked: Boolean = false,

    @ColumnInfo("is_cached")
    val isCached: Boolean = false,

    @ColumnInfo("created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo("updated_at")
    val updatedAt: Long = System.currentTimeMillis()
)