package com.amangarg.samachar.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)