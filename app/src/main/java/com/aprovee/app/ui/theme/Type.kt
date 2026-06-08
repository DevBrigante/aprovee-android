package com.aprovee.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aprovee.app.R

val JakartaFontFamily = FontFamily(
    Font(R.font.plus_jakarta_sans_regular, weight = FontWeight.Normal),
    Font(R.font.plus_jakarta_sans_medium, weight = FontWeight.Medium),
    Font(R.font.plus_jakarta_sans_semibold, weight = FontWeight.SemiBold),
    Font(R.font.plus_jakarta_sans_bold, weight = FontWeight.Bold),
    Font(R.font.plus_jakarta_sans_extrabold, weight = FontWeight.ExtraBold),
)

val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = JakartaFontFamily, fontWeight = FontWeight.ExtraBold,
        fontSize = 34.sp, lineHeight = 40.sp
    ),
    displaySmall = TextStyle(
        fontFamily = JakartaFontFamily, fontWeight = FontWeight.Bold,
        fontSize = 26.sp, lineHeight = 32.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = JakartaFontFamily, fontWeight = FontWeight.Bold,
        fontSize = 26.sp, lineHeight = 32.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = JakartaFontFamily, fontWeight = FontWeight.Bold,
        fontSize = 21.sp, lineHeight = 28.sp
    ),
    titleLarge = TextStyle(
        fontFamily = JakartaFontFamily, fontWeight = FontWeight.Bold,
        fontSize = 21.sp, lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = JakartaFontFamily, fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp, lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = JakartaFontFamily, fontWeight = FontWeight.Normal,
        fontSize = 16.sp, lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = JakartaFontFamily, fontWeight = FontWeight.Normal,
        fontSize = 14.sp, lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = JakartaFontFamily, fontWeight = FontWeight.Medium,
        fontSize = 12.sp, lineHeight = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = JakartaFontFamily, fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp, lineHeight = 18.sp
    ),
    labelSmall = TextStyle(
        fontFamily = JakartaFontFamily, fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp, lineHeight = 18.sp
    )
)