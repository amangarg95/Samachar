package com.amangarg.samachar.ui.composable.screen.filters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.amangarg.samachar.ui.model.FilterCategory
import com.amangarg.samachar.ui.theme.LocalDimensions
import com.amangarg.samachar.ui.theme.VintageText

@Composable
fun FilterItem(
    modifier: Modifier,
    category: FilterCategory,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val dimensions = LocalDimensions.current

    Row(
        modifier = modifier
            .clickable { onClick() }
            .wrapContentWidth()
            .padding(bottom = dimensions.verticalPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = category.icon),
            contentDescription = "Filter Icon",
            tint = VintageText
        )
        Spacer(modifier = Modifier.width(4.dp))
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