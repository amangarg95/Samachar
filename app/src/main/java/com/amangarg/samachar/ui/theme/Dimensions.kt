package com.amangarg.samachar.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val textPadding: Dp = 4.dp,
    val horizontalPadding: Dp = 16.dp,
    val verticalPadding: Dp = 8.dp,
    val dividerThicknessLarge: Dp = 1.dp,
    val dividerThicknessSmall: Dp = 0.5.dp,
    val dividerSpacingSmall: Dp = 1.dp
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }
