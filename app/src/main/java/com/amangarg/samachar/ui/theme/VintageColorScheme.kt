package com.amangarg.samachar.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val VintageBackground = Color(0xFFE8CFA8)
val VintagePrimary = Color(0xFF8B4513)
val VintageSecondary = Color(0xFFCD853F)
val VintageAccent = Color(0xFFD2691E)
val VintageText = Color(0xFF2F1B14)
val VintageTextSecondary = Color(0xFF5D4037)
val VintageCard = Color(0xFFE8CFA8)
val VintageColorScheme = lightColorScheme(
    primary = VintagePrimary,
    secondary = VintageSecondary,
    tertiary = VintageAccent,
    background = VintageBackground,
    surface = VintageCard,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = VintageText,
    onSurface = VintageText,
)
