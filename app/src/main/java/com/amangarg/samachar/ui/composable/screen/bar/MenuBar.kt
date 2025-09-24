package com.amangarg.samachar.ui.composable.screen.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amangarg.samachar.ui.navigation.SamacharMenu
import com.amangarg.samachar.ui.theme.VintageCard

@Composable
fun MenuBar(
    selectedCategory: SamacharMenu,
    onCategorySelected: (SamacharMenu) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .padding(12.dp)
            .background(VintageCard)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(SamacharMenu.entries.dropLast(1).toTypedArray()) { menuTabItem ->
            SamacharMenuItem(
                samacharMenuTab = menuTabItem,
                isSelected = menuTabItem == selectedCategory,
                onClick = { onCategorySelected(menuTabItem) }
            )
        }
    }
}