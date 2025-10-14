package com.amangarg.samachar.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.amangarg.samachar.ui.activity.MainViewModel
import com.amangarg.samachar.ui.composable.screen.bar.SamacharTopBar
import com.amangarg.samachar.ui.navigation.navgraph.articleDetailsScreen
import com.amangarg.samachar.ui.navigation.navgraph.bookmarksScreen
import com.amangarg.samachar.ui.navigation.navgraph.filtersScreen
import com.amangarg.samachar.ui.navigation.navgraph.searchScreen
import com.amangarg.samachar.ui.navigation.navgraph.topHeadlinesScreen
import com.amangarg.samachar.ui.viewmodel.TopHeadlinesViewModel

@Composable
fun SamacharNav(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val selectedCategory = rememberSaveable { mutableStateOf(SamacharMenu.TOP_HEADLINES) }
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
    val topHeadlinesViewModel: TopHeadlinesViewModel = hiltViewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SamacharTopBar(
                selectedCategory = selectedCategory.value,
                onMenuTabSelected = {
                    selectedCategory.value = it
                },
                navController = navController,
                region = uiState.currentRegion.uppercase(),
                date = uiState.date,
                language = uiState.currentLanguage.uppercase()
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = SamacharMenu.TOP_HEADLINES.route,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            topHeadlinesScreen(navController, topHeadlinesViewModel, mainViewModel)
            searchScreen(navController)
            bookmarksScreen(navController)
            filtersScreen(navController, mainViewModel)
            articleDetailsScreen()
        }
    }
}