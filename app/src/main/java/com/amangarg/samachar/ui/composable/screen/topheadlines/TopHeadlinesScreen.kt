package com.amangarg.samachar.ui.composable.screen.topheadlines

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amangarg.samachar.domain.model.Article
import com.amangarg.samachar.ui.UiState
import com.amangarg.samachar.ui.UiState.*
import com.amangarg.samachar.ui.activity.MainViewModel
import com.amangarg.samachar.ui.composable.screen.articlelist.ArticleList
import com.amangarg.samachar.ui.theme.VintageBackground
import com.amangarg.samachar.ui.viewmodel.TopHeadlinesViewModel
import com.amangarg.samachar.ui.composable.ErrorContent
import com.amangarg.samachar.ui.composable.LoadingContent

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeadlinesScreen(
    onArticleClick: (Article) -> Unit,
    onBookmarkArticle: (Article) -> Unit,
    topHeadlinesViewModel: TopHeadlinesViewModel,
    mainViewModel: MainViewModel
) {
    val uiState: UiState<List<Article>> by topHeadlinesViewModel.state.collectAsStateWithLifecycle()
    val mainUiState = mainViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(mainUiState.value.currentRegion) {
        topHeadlinesViewModel.updateCountry(mainUiState.value.currentRegion)
    }
    LaunchedEffect(mainUiState.value.currentLanguage) {
        topHeadlinesViewModel.updateLanguage(mainUiState.value.currentLanguage)
    }
    LaunchedEffect(mainUiState.value.currentLanguage) {
        topHeadlinesViewModel.updateSource(mainUiState.value.currentLanguage)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VintageBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            when (uiState) {
                is Loading -> {
                    LoadingContent()
                }

                is Success<*> -> {
                    val articles = (uiState as Success<List<Article>>).data
                    ArticleList(
                        articles = articles,
                        onArticleClick = onArticleClick,
                        onBookmarkArticle = onBookmarkArticle
                    )
                }

                is Error -> {
                    ErrorContent(
                        message = (uiState as Error).message,
                        onRetry = {
//                            viewModel.getTopHeadlinesByCountry("")
                        }
                    )
                }

                Idle -> {
                    // Show loading for initial state
                    LoadingContent()
                }
            }
        }
    }
}
