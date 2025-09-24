package com.amangarg.samachar.ui.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.amangarg.samachar.ui.composable.screen.articledetails.ArticleDetailsScreen
import com.amangarg.samachar.ui.navigation.Menu

fun NavGraphBuilder.articleDetailsScreen() {
    composable(
        route = "${Menu.ARTICLE_DETAILS.route}/{articleUrl}",
        arguments = listOf(navArgument("articleUrl") { defaultValue = "" })
    ) { backStackEntry ->
        val articleUrl = backStackEntry.arguments?.getString("articleUrl") ?: ""
        ArticleDetailsScreen(
            articleUrl = articleUrl,
            onBackClick = { }
        )
    }
}