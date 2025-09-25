package com.amangarg.samachar.domain.model

data class Article(
    val source: Source? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val imageUrl: String? = null,
    val publishedAt: String? = null,
    val content: String? = null,
    val isBookmarked: Boolean
)