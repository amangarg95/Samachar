package com.amangarg.samachar.data.remote.dto

data class NewsDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)