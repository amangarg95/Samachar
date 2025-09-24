package com.amangarg.samachar.ui.composable.screen.bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.amangarg.samachar.ui.navigation.SamacharMenu
import com.amangarg.samachar.ui.theme.VintageText

@Composable
fun SamacharMenuItem(
    samacharMenuTab: SamacharMenu,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }

            .padding(8.dp)
    ) {
        Text(
            text = samacharMenuTab.displayName.uppercase(),
            style = MaterialTheme.typography.bodyMedium.copy(
                textDecoration = if (isSelected) TextDecoration.Underline else TextDecoration.None
            ),
            color = VintageText
        )
    }
}