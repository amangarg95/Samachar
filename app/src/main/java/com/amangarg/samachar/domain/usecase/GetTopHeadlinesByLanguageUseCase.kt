package com.amangarg.samachar.domain.usecase

import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.domain.repository.NewsRepository
import com.amangarg.samachar.util.AppConstants.DEFAULT_PAGE_NUM
import com.amangarg.samachar.util.AppConstants.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopHeadlinesByLanguageUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(
        language: String,
        pageNum: Int = DEFAULT_PAGE_NUM,
        pageSize: Int = DEFAULT_PAGE_SIZE
    ): Flow<List<Article>> {
        return repository.getTopHeadlinesByLanguage(
            language = language,
            pageNum = pageNum,
            pageSize = pageSize
        )
    }
}







