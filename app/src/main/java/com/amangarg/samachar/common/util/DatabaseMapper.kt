package com.amangarg.samachar.common.util

import com.amangarg.samachar.data.local.database.entity.ArticleEntity
import com.amangarg.samachar.data.local.database.entity.SourceEntity
import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.domain.model.Source

// Entity to Domain
fun ArticleEntity.toDomain(): Article {
    return Article(
        source = source.toDomain(),
        author = author,
        title = title,
        description = description,
        url = url,
        imageUrl = urlToImage,
        publishedAt = publishedAt,
        content = content,
        isBookmarked = isBookmarked
    )
}

fun SourceEntity.toDomain(): Source {
    return Source(
        id = id ?: "",
        name = name ?: ""
    )
}

// Domain to Entity
fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        source = source?.toEntity() ?: SourceEntity(null, null),
        author = author,
        title = title ?: "",
        description = description,
        url = url ?: "",
        urlToImage = imageUrl,
        publishedAt = publishedAt,
        content = content,
        isBookmarked = isBookmarked
    )
}

fun Source.toEntity(): SourceEntity {
    return SourceEntity(
        id = id,
        name = name
    )
}