package com.amangarg.samachar.ui.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.amangarg.samachar.ui.activity.MainViewModel
import com.amangarg.samachar.ui.composable.screen.filters.FiltersScreen
import com.amangarg.samachar.ui.navigation.SamacharMenu
import com.amangarg.samachar.util.AppConstants

fun NavGraphBuilder.filtersScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    composable(SamacharMenu.FILTERS.route) {
        FiltersScreen(
            countryList = AppConstants.countryList,
            languageList = AppConstants.languageList,
            onCountryToggle = {
                mainViewModel.updateRegion(it.name.uppercase())
                navController.navigate(SamacharMenu.TOP_HEADLINES.route) {
                    popUpTo(SamacharMenu.TOP_HEADLINES.route) { inclusive = true }
                    launchSingleTop = true
                }
            },
            onLanguageToggle = {
                mainViewModel.updateLanguage(it.name.uppercase())
                navController.navigate(SamacharMenu.TOP_HEADLINES.route) {
                    popUpTo(SamacharMenu.TOP_HEADLINES.route) { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
    }
}
