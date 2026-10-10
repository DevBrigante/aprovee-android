package com.aprovee.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = BrandLight,
    onPrimary = OnBrandLight,
    primaryContainer = BrandSoftLight,
    onPrimaryContainer = OnBrandSoftLight,
    secondary = BrightLight,
    onSecondary = OnBrandLight,
    background = BgLight,
    onBackground = Text1Light,
    surface = CardLight,
    onSurface = Text1Light,
    surfaceVariant = FillLight,
    onSurfaceVariant = Text2Light,
    surfaceContainerLowest = CardLight,
    surfaceContainerLow = BgLight,
    surfaceContainer = CardLight,
    surfaceContainerHigh = CardLight,
    surfaceContainerHighest = FillLight,
    outline = LineLight,
    outlineVariant = LineLight,
    error = OverdueLight,
    onError = Color.White,
    errorContainer = OverdueContainerLight,
    onErrorContainer = OverdueLight,
    inverseSurface = ToastBgLight,
    inverseOnSurface = ToastTextLight,
    inversePrimary = ToastActionLight,
    scrim = ScrimLight,
)

private val DarkColorScheme = darkColorScheme(
    primary = BrandDark,
    onPrimary = OnBrandDark,
    primaryContainer = BrandSoftDark,
    onPrimaryContainer = OnBrandSoftDark,
    secondary = BrightDark,
    onSecondary = OnBrandDark,
    background = BgDark,
    onBackground = Text1Dark,
    surface = CardDark,
    onSurface = Text1Dark,
    surfaceVariant = FillDark,
    onSurfaceVariant = Text2Dark,
    surfaceContainerLowest = CardDark,
    surfaceContainerLow = BgDark,
    surfaceContainer = CardDark,
    surfaceContainerHigh = CardDark,
    surfaceContainerHighest = FillDark,
    outline = LineDark,
    outlineVariant = LineDark,
    error = OverdueDark,
    onError = Color.White,
    errorContainer = OverdueContainerDark,
    onErrorContainer = OverdueDark,
    inverseSurface = ToastBgDark,
    inverseOnSurface = ToastTextDark,
    inversePrimary = ToastActionDark,
    scrim = ScrimDark,
)

@Composable
fun AproveeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val aproveeColors = if (darkTheme) DarkAproveeColors else LightAproveeColors
    CompositionLocalProvider(LocalAproveeColors provides aproveeColors) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
            typography = AppTypography,
            shapes = AproveeShapes,
            content = content
        )
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        SideEffect {
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }
}

object AproveeTheme {
    val colors: AproveeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAproveeColors.current
}