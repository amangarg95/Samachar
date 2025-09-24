package com.amangarg.samachar.ui.composable.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.ui.UiState
import com.amangarg.samachar.ui.composable.screen.articlelist.ArticleList
import com.amangarg.samachar.ui.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    onArticleClick: (Article) -> Unit,
    onBookmarkArticle: (Article) -> Unit,
    viewModel: SearchViewModel
) {
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchState by viewModel.searchState.collectAsStateWithLifecycle()
    val hasSearched by viewModel.hasSearched.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search news...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.clearSearch() }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { viewModel.searchNews(searchQuery) }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (searchState) {
            is UiState.Idle -> {
                if (!hasSearched) {
                    Text(
                        text = "Start typing to search news",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Success -> {
                val articles = (searchState as UiState.Success<List<Article>>).data
                ArticleList(
                    articles = articles,
                    onArticleClick = onArticleClick,
                    onBookmarkArticle = onBookmarkArticle
                )
            }

            is UiState.Error -> {
                val message = (searchState as UiState.Error).message
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
