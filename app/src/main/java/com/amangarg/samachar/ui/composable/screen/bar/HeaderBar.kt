package com.amangarg.samachar.ui.composable.screen.bar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.amangarg.samachar.ui.theme.VintageText
import com.amangarg.samachar.ui.theme.LocalDimensions

@Composable
fun HeaderBar(
    title: String,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensions.horizontalPadding,
                vertical = dimensions.verticalPadding
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(
            modifier = Modifier.padding(bottom = dimensions.dividerSpacingSmall),
            color = VintageText,
            thickness = dimensions.dividerThicknessLarge
        )
        HorizontalDivider(
            color = VintageText,
            thickness = dimensions.dividerThicknessSmall
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Thin
            ),
            color = Color.Black
        )
        HorizontalDivider(
            modifier = Modifier.padding(bottom = dimensions.dividerSpacingSmall),
            color = VintageText,
            thickness = dimensions.dividerThicknessSmall
        )
        HorizontalDivider(
            color = VintageText,
            thickness = dimensions.dividerThicknessLarge
        )
    }
}
