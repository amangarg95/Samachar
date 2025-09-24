package com.amangarg.samachar.ui.composable.screen.bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.amangarg.samachar.ui.navigation.SamacharMenu

@Composable
fun SamacharTopBar(
    navController: NavHostController,
    selectedCategory: SamacharMenu,
    onMenuTabSelected: (SamacharMenu) -> Unit,
    region: String,
    date: String,
    language: String
) {
    Column(modifier = Modifier.statusBarsPadding()) {
        HeaderBar("SAMACHAR")
        HeaderInfoBar(
            region = region,
            date = date,
            language = language
        )
        MenuBar(
            selectedCategory = selectedCategory,
            onCategorySelected = { category ->
                onMenuTabSelected(category)
                navController.navigate(category.route) {
                    popUpTo(SamacharMenu.TOP_HEADLINES.route) { inclusive = false }
                    launchSingleTop = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}