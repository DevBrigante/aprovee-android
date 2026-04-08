package com.aprovee.app.ui.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aprovee.app.R
import com.aprovee.app.ui.components.AproveeIcon
import com.aprovee.app.ui.theme.AproveeTheme
import com.aprovee.app.ui.theme.BackgroundDark
import com.aprovee.app.ui.theme.Brand
import com.aprovee.app.ui.theme.BrandDark
import com.aprovee.app.ui.theme.BrandLight
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
        delay(2500L)
        onNavigateToLogin()
    }
    SplashContent(isVisible = isVisible, isDark = isSystemInDarkTheme())
}


@Composable
fun SplashContent(
    isVisible: Boolean,
    isDark: Boolean = false
) {
    val backgroundColor = if(isDark) BackgroundDark else Brand
    val backgroundTint = if(isDark) BrandDark else BrandLight

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 800
                )
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.weight(0.35f))
                AproveeIcon(
                    size = 80.dp,
                    backgroundTint = backgroundTint
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
                Text(
                    text = stringResource(R.string.splash_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Spacer(
                    modifier = Modifier
                        .height(40.dp)
                )
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.White.copy(alpha = 0.4f))
                )
                Spacer(modifier = Modifier.weight(0.65f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenLightPreview() {
    AproveeTheme(darkTheme = false) {
        SplashContent(isVisible = true, isDark = false)
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenDarkPreview() {
    AproveeTheme(darkTheme = true) {
        SplashContent(isVisible = true, isDark = true)
    }
}