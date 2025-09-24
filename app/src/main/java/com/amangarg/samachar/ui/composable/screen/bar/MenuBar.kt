package com.amangarg.samachar.ui.composable.screen.bar

import androidx.compose.foundation.layout.Arrangement
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
    Card(
        modifier = modifier
            .wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = VintageCard
        )
    ) {
        LazyRow(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(SamacharMenu.entries.toTypedArray()) { menuTabItem ->
                SamacharMenuItem(
                    samacharMenuTab = menuTabItem,
                    isSelected = menuTabItem == selectedCategory,
                    onClick = { onCategorySelected(menuTabItem) }
                )
            }
        }
    }
}