package com.amangarg.samachar.domain.usecase

import com.amangarg.samachar.domain.model.Source
import com.amangarg.samachar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Source>> {
        return repository.getSources()
    }
}