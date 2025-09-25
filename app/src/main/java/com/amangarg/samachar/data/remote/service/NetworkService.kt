package com.amangarg.samachar.data.remote.service


import com.amangarg.samachar.data.remote.dto.NewsDto
import com.amangarg.samachar.data.remote.dto.SourcesDto
import com.amangarg.samachar.common.util.AppConstants.DEFAULT_COUNTRY
import com.amangarg.samachar.common.util.AppConstants.DEFAULT_LANGUAGE
import com.amangarg.samachar.common.util.AppConstants.DEFAULT_PAGE_NUM
import com.amangarg.samachar.common.util.AppConstants.DEFAULT_PAGE_SIZE
import com.amangarg.samachar.common.util.NetworkConstants.COUNTRY_KEY
import com.amangarg.samachar.common.util.NetworkConstants.LANGUAGE_KEY
import com.amangarg.samachar.common.util.NetworkConstants.PAGE_NO_KEY
import com.amangarg.samachar.common.util.NetworkConstants.PAGE_SIZE_KEY
import com.amangarg.samachar.common.util.NetworkConstants.QUERY_KEY
import com.amangarg.samachar.common.util.NetworkConstants.SOURCES_KEY
import org.intellij.lang.annotations.Flow.DEFAULT_SOURCE
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("everything")
    suspend fun searchNews(
        @Query(QUERY_KEY) searchQuery: String,
        @Query(PAGE_NO_KEY) pageNum: Int = DEFAULT_PAGE_NUM,
        @Query(PAGE_SIZE_KEY) pageSize: Int = DEFAULT_PAGE_SIZE,
    ): NewsDto

    @GET("top-headlines")
    suspend fun getTopHeadlinesByCountry(
        @Query(COUNTRY_KEY) country: String = DEFAULT_COUNTRY,
        @Query(PAGE_NO_KEY) pageNum: Int = DEFAULT_PAGE_NUM,
        @Query(PAGE_SIZE_KEY) pageSize: Int = DEFAULT_PAGE_SIZE,
    ): NewsDto

    @GET("top-headlines")
    suspend fun getTopHeadlinesByLanguage(
        @Query(LANGUAGE_KEY) language: String = DEFAULT_LANGUAGE,
        @Query(PAGE_NO_KEY) pageNum: Int = DEFAULT_PAGE_NUM,
        @Query(PAGE_SIZE_KEY) pageSize: Int = DEFAULT_PAGE_SIZE,
    ): NewsDto

    @GET("top-headlines")
    suspend fun getTopHeadlinesBySource(
        @Query(SOURCES_KEY) sources: String = DEFAULT_SOURCE,
        @Query(PAGE_NO_KEY) pageNum: Int = DEFAULT_PAGE_NUM,
        @Query(PAGE_SIZE_KEY) pageSize: Int = DEFAULT_PAGE_SIZE,
    ): NewsDto

    @GET("sources")
    suspend fun getSources(): SourcesDto
}