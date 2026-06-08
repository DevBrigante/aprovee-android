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
    primary            = PrimaryLight,
    onPrimary          = SurfaceLight,
    primaryContainer   = PrimaryContainerLight,
    onPrimaryContainer = PrimaryLight,
    secondary          = PrimaryBrightLight,
    onSecondary        = SurfaceLight,
    background         = BgLight,
    onBackground       = TextLight,
    surface            = SurfaceLight,
    onSurface          = TextLight,
    surfaceVariant     = InputBgLight,
    onSurfaceVariant   = TextSecondaryLight,
    outline            = BorderLight,
    outlineVariant     = BorderLight,
    error              = OverdueLight,
    errorContainer     = ErrorContainerLight,
    onErrorContainer   = OnErrorContainerLight
)

private val DarkColorScheme = darkColorScheme(
    primary            = PrimaryDark,
    onPrimary          = BgDark,
    primaryContainer   = PrimaryContainerDark,
    onPrimaryContainer = PrimaryBrightDark,
    secondary          = PrimaryBrightDark,
    onSecondary        = BgDark,
    background         = BgDark,
    onBackground       = TextDark,
    surface            = SurfaceDark,
    onSurface          = TextDark,
    surfaceVariant     = InputBgDark,
    onSurfaceVariant   = TextSecondaryDark,
    outline            = BorderDark,
    outlineVariant     = BorderDark,
    error              = OverdueDark,
    errorContainer     = ErrorContainerDark,
    onErrorContainer   = OnErrorContainerDark
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