package com.amangarg.samachar.domain.repository

import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.domain.model.Source
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun searchNews(
        query: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<List<Article>>

    fun getTopHeadlinesByCountry(
        country: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<List<Article>>

    fun getTopHeadlinesBySource(
        source: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<List<Article>>

    fun getTopHeadlinesByLanguage(
        language: String,
        pageNum: Int,
        pageSize: Int
    ): Flow<List<Article>>

    fun getSources(): Flow<List<Source>>
}