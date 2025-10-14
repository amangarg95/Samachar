package com.amangarg.samachar.ui.composable.screen.articlelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.ui.theme.VintageText
import kotlin.collections.drop

@Composable
fun ArticleList(
    articles: List<Article>,
    onArticleClick: (Article) -> Unit,
    onBookmarkArticle: (Article) -> Unit,
) {

    if (articles.isEmpty()) return

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp
    ) {
        item(
            span = StaggeredGridItemSpan.FullLine,
            content = {
                val article = articles[0]

                Column {
                    ArticleItem(
                        article = article,
                        onArticleClick = { onArticleClick(article) },
                        onBookmarkArticle = { onBookmarkArticle(article) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )

                    HorizontalDivider(color = VintageText, thickness = 0.5.dp)
                }
            }
        )

        items(
            items = articles.drop(1),
            key = { it.url ?: it.title ?: it.hashCode() }
        ) { article ->
            ArticleItem(
                article = article,
                onArticleClick = { onArticleClick(article) },
                onBookmarkArticle = { onBookmarkArticle(article) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )
        }
    }
}

