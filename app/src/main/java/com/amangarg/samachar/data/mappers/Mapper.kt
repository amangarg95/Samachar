package com.amangarg.samachar.data.mappers

import com.amangarg.samachar.data.remote.dto.ArticleDto
import com.amangarg.samachar.data.remote.dto.SourceDto
import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.domain.model.Source
import kotlin.text.orEmpty

fun ArticleDto.toDomain(): Article {
    return Article(
        source = source?.toDomain(),
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        imageUrl = imageUrl.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        content = content.orEmpty()
    )
}

fun SourceDto.toDomain(): Source {
    return Source(
        id = id.orEmpty(),
        name = name.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        category = category.orEmpty(),
        language = language.orEmpty(),
        country = country.orEmpty()
    )
}

