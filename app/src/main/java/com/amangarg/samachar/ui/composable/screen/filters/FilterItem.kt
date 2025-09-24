package com.amangarg.samachar.ui.composable.screen.filters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.amangarg.samachar.ui.model.FilterCategory
import com.amangarg.samachar.ui.theme.VintageText

@Composable
fun FilterItem(
    modifier: Modifier,
    category: FilterCategory,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(
            text = category.displayName,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                textDecoration = if (isSelected) TextDecoration.Underline else TextDecoration.None
            ),
            color = VintageText
        )
    }
}