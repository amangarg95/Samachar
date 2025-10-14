package com.amangarg.samachar.ui.composable.screen.articlelist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.amangarg.samachar.R
import com.amangarg.samachar.common.util.Util.formatDateLegacy
import com.amangarg.samachar.domain.model.Article

@Composable
fun ArticleItem(
    article: Article,
    onArticleClick: (Article) -> Unit,
    onBookmarkArticle: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    val formattedDate = remember(article.publishedAt) {
        article.publishedAt?.let { formatDateLegacy(it) } ?: ""
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onArticleClick(article) },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RectangleShape
    ) {
        Column {
            Box {
                AsyncImage(
                    model = article.imageUrl,
                    contentDescription = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 180.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    error = painterResource(id = R.drawable.ic_launcher_foreground)
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color(0x66000000))
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = article.title.orEmpty(),
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.description.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    article.author?.let {
                        Text(
                            text = "By $it",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    article.publishedAt?.let {
                        Text(
                            text = formattedDate,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                    }


                    Text(
                        text = article.source?.name ?: "Unknown",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )

                }
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onBookmarkArticle(article)
                        },
                    painter = painterResource(R.drawable.ic_bookmark_unselected),
                    contentDescription = "Bookmark",
                    tint = MaterialTheme.colorScheme.primary
                )

            }
        }
    }
}
