package com.amangarg.samachar.ui.navigation.navgraph

import android.net.Uri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.amangarg.samachar.ui.composable.screen.search.SearchScreen
import com.amangarg.samachar.ui.navigation.SamacharMenu
import com.amangarg.samachar.ui.viewmodel.SearchViewModel

fun NavGraphBuilder.searchScreen(navController: NavController) {
    composable(SamacharMenu.SEARCH.route) {
        val searchViewModel: SearchViewModel = hiltViewModel()
        SearchScreen(
            onArticleClick = { article ->
                article.url?.let { url ->
                    navController.navigate("article_details/${Uri.encode(url)}/${Uri.encode(article.title ?: "")}")
                }
            },
            onBookmarkArticle = {},
            viewModel = searchViewModel
        )
    }
}