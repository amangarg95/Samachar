package com.amangarg.samachar.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun SamacharTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = VintageColorScheme,
        typography = VintageTypography,
        content = content
    )
}