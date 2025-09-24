package com.amangarg.samachar.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.amangarg.samachar.R

val TimesNewRoman = FontFamily(
    Font(R.font.times)
)
val VintageTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = TimesNewRoman,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = VintageText
    ),
    headlineMedium = TextStyle(
        fontFamily = TimesNewRoman,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = VintageText
    ),
    titleLarge = TextStyle(
        fontFamily = TimesNewRoman,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = VintageText
    ),
    bodyLarge = TextStyle(
        fontFamily = TimesNewRoman,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = VintageText
    ),
    bodyMedium = TextStyle(
        fontFamily = TimesNewRoman,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = VintageTextSecondary
    ),
    labelMedium = TextStyle(
        fontFamily = TimesNewRoman,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = VintageTextSecondary
    )
)
