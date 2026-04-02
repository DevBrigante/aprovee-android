package com.aprovee.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary          = Brand,
    onPrimary        = SurfaceLight,
    primaryContainer = BrandTint,
    onPrimaryContainer = BrandDark,
    secondary        = AccentGold,
    onSecondary      = SurfaceLight,
    background       = BackgroundLight,
    onBackground     = TextPrimary,
    surface          = SurfaceLight,
    onSurface        = TextPrimary,
    onSurfaceVariant = TextSecondary,
    outline          = Border,
    error            = Error,
)

private val DarkColorScheme = darkColorScheme(
    primary          = Brand,
    onPrimary        = Navy,
    primaryContainer = BrandDark,
    onPrimaryContainer = BrandLight,
    secondary        = AccentGold,
    onSecondary      = Navy,
    background       = BackgroundDark,
    onBackground     = TextPrimaryDark,
    surface          = SurfaceDark,
    onSurface        = SurfaceLight,
    onSurfaceVariant = TextSecondaryDark,
    outline          = BorderDark,
    error            = Error,
)

@Composable
fun AproveeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = AppTypography,
        content = content
    )
}