package com.aprovee.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

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
    surfaceVariant = FieldBackground,
    onSurface        = TextPrimary,
    onSurfaceVariant = TextSecondary,
    outline          = Border,
    outlineVariant = Border,
    error            = Error,
    errorContainer = FieldBackgroundError
)

private val DarkColorScheme = darkColorScheme(
    primary          = Brand,
    onPrimary        = TextPrimaryDark,
    primaryContainer = BrandDark,
    onPrimaryContainer = BrandLight,
    secondary        = AccentGold,
    onSecondary      = TextPrimaryDark,
    background       = BackgroundDark,
    onBackground     = TextPrimaryDark,
    surface          = SurfaceDark,
    surfaceVariant = FieldBackgroundDark,
    onSurface        = TextPrimaryDark,
    onSurfaceVariant = TextSecondary,
    outline          = BorderDark,
    outlineVariant = BorderDark,
    error            = ErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark
)

@Composable
fun AproveeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = AppTypography,
        content = content
    )
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        SideEffect {
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }
}