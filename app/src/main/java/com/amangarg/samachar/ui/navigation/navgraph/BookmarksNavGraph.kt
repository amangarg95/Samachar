package com.amangarg.samachar.ui.navigation.navgraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.amangarg.samachar.ui.navigation.Menu

fun NavGraphBuilder.bookmarksScreen(navController: NavHostController) {
    composable(Menu.BOOKMARKS.route) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Bookmarks Screen")
        }
    }
}