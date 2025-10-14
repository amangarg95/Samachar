package com.amangarg.samachar.ui.navigation.navgraph

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.amangarg.samachar.ui.activity.MainViewModel
import com.amangarg.samachar.ui.composable.screen.filters.FiltersScreen
import com.amangarg.samachar.ui.navigation.SamacharMenu
import com.amangarg.samachar.ui.viewmodel.FiltersScreenViewModel
import com.amangarg.samachar.common.util.AppConstants

fun NavGraphBuilder.filtersScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    composable(SamacharMenu.FILTERS.route) {
        val filtersViewModel: FiltersScreenViewModel = hiltViewModel()
        FiltersScreen(
            countryList = AppConstants.countryList,
            languageList = AppConstants.languageList,
            onCountryToggle = {
                mainViewModel.setRegion(it.code.uppercase())
                navController.navigate(SamacharMenu.TOP_HEADLINES.route) {
                    popUpTo(SamacharMenu.TOP_HEADLINES.route) { inclusive = true }
                    launchSingleTop = true
                }
            },
            onLanguageToggle = {
                mainViewModel.setLanguage(it.code.uppercase())
                navController.navigate(SamacharMenu.TOP_HEADLINES.route) {
                    popUpTo(SamacharMenu.TOP_HEADLINES.route) { inclusive = true }
                    launchSingleTop = true
                }
            },
            filtersViewModel = filtersViewModel
        )
    }
}
