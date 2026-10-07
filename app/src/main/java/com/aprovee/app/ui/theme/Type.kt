package com.aprovee.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.aprovee.app.R

val GeistFontFamily = FontFamily(
    Font(R.font.geist_regular, weight = FontWeight.Normal),
    Font(R.font.geist_medium, weight = FontWeight.Medium),
    Font(R.font.geist_semibold, weight = FontWeight.SemiBold),
    Font(R.font.geist_bold, weight = FontWeight.Bold)

)

val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.SemiBold,
        fontSize = 44.sp, lineHeight = 48.sp, letterSpacing = (-0.04).em
    ),
    headlineLarge = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.Bold,
        fontSize = 30.sp, lineHeight = 33.sp, letterSpacing = (-0.035).em
    ),
    headlineMedium = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.Bold,
        fontSize = 28.sp, lineHeight = 32.sp, letterSpacing = (-0.035).em
    ),
    headlineSmall = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp, lineHeight = 26.sp, letterSpacing = (-0.025).em
    ),
    titleLarge = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp, lineHeight = 24.sp, letterSpacing = (-0.02).em
    ),
    titleMedium = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp, lineHeight = 22.sp, letterSpacing = (-0.01).em
    ),
    titleSmall = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp, lineHeight = 21.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.Normal,
        fontSize = 15.5.sp, lineHeight = 23.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.Normal,
        fontSize = 13.sp, lineHeight = 18.sp
    ),
    bodySmall = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.Normal,
        fontSize = 12.5.sp, lineHeight = 17.sp
    ),
    labelLarge = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp, lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp, lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = GeistFontFamily, fontWeight = FontWeight.Medium,
        fontSize = 10.5.sp, lineHeight = 14.sp
    )
)