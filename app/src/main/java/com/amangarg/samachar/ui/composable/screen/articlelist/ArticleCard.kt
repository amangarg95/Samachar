package com.amangarg.samachar.ui.composable.screen.articlelist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.amangarg.samachar.domain.model.Article
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import com.amangarg.samachar.R

@Composable
fun ArticleCard(
    article: Article,
    onArticleClick: (Article) -> Unit,
    onBookmarkArticle: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onArticleClick(article) },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Box {
                AsyncImage(
                    model = article.imageUrl,
                    contentDescription = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 180.dp), // ensure consistent card height
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
                text = article.content.orEmpty(),
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
                            text = formatDateLegacy(it),
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


fun formatDateLegacy(input: String): String {

    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    parser.timeZone = TimeZone.getTimeZone("UTC")
    val date = parser.parse(input)
    val formatter = SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault())
    return formatter.format(date!!)
}