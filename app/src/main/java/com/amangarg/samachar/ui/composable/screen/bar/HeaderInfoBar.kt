package com.amangarg.samachar.ui.composable.screen.bar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.amangarg.samachar.ui.theme.VintageText
import com.amangarg.samachar.ui.theme.LocalDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderInfoBar(
    region: String,
    date: String,
    language: String,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.horizontalPadding)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = region,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = date,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(bottom = dimensions.textPadding),
                text = language,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                textAlign = TextAlign.End
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(bottom = dimensions.dividerSpacingSmall),
            color = VintageText,
            thickness = dimensions.dividerThicknessSmall
        )
        HorizontalDivider(
            color = VintageText,
            thickness = dimensions.dividerThicknessSmall
        )
    }
}
