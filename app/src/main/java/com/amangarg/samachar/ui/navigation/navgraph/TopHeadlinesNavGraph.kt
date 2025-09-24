package com.amangarg.samachar.ui.navigation.navgraph

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.amangarg.samachar.ui.activity.MainViewModel
import com.amangarg.samachar.ui.composable.screen.topheadlines.TopHeadlinesScreen
import com.amangarg.samachar.ui.navigation.Menu
import com.amangarg.samachar.ui.viewmodel.TopHeadlinesViewModel

fun NavGraphBuilder.topHeadlinesScreen(
    navController: NavController,
    topHeadlinesViewModel: TopHeadlinesViewModel,
    mainViewModel: MainViewModel
) {
    composable(
        route = Menu.TOP_HEADLINES.route
    ) {
        TopHeadlinesScreen(
            onArticleClick = { article ->
                article.url?.let { url ->
                    navController.navigate(
                        "article_details/${Uri.encode(url)}"
                    )
                }
            },
            onBookmarkArticle = { article ->
                topHeadlinesViewModel.bookMarkArticle(article)
            },
            mainViewModel = mainViewModel,
            viewModel = topHeadlinesViewModel,
        )
    }
}