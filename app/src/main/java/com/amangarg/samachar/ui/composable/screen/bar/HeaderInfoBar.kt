package com.amangarg.samachar.ui.composable.screen.bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amangarg.samachar.R
import com.amangarg.samachar.ui.theme.LocalDimensions
import com.amangarg.samachar.ui.theme.VintageText

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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(bottom = dimensions.textPadding),
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_region),
                    contentDescription = "Region Icon",
                    tint = VintageText
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = region,
                    style = MaterialTheme.typography.bodySmall,
                    color = VintageText
                )
            }
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = date,
                style = MaterialTheme.typography.bodySmall,
                color = VintageText,
                textAlign = TextAlign.Center
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(bottom = dimensions.textPadding),
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_language),
                    contentDescription = "Region Icon",
                    tint = VintageText
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    modifier = Modifier
                        .wrapContentWidth(),
                    text = language,
                    style = MaterialTheme.typography.bodySmall,
                    color = VintageText
                )
            }
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
