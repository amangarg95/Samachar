package com.amangarg.samachar.ui.composable.screen.bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.amangarg.samachar.ui.theme.LocalDimensions
import com.amangarg.samachar.ui.theme.VintageText

@Composable
fun FilterBar(
    title: String,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.horizontalPadding)
    ) {
        HorizontalDivider(
            modifier = Modifier.padding(bottom = dimensions.dividerSpacingSmall),
            color = VintageText,
            thickness = dimensions.dividerThicknessSmall
        )
        HorizontalDivider(color = VintageText, thickness = dimensions.dividerThicknessSmall)

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.textPadding),
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        HorizontalDivider(
            modifier = Modifier.padding(bottom = dimensions.dividerSpacingSmall),
            color = VintageText,
            thickness = dimensions.dividerThicknessSmall
        )
        HorizontalDivider(color = VintageText, thickness = dimensions.dividerThicknessSmall)
    }
}
