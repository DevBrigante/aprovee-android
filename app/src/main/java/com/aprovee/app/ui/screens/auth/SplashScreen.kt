package com.aprovee.app.ui.screens.auth

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aprovee.app.R
import com.aprovee.app.ui.theme.AproveeTheme
import com.aprovee.app.ui.theme.PrimaryDark
import com.aprovee.app.ui.theme.SplashBgDark
import com.aprovee.app.ui.theme.SplashBgLight
import com.aprovee.app.ui.theme.SplashLogoDark
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun SplashScreen(
    onTimeout: () -> Unit,
    isDark: Boolean = isSystemInDarkTheme()
) {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.avd_aprovee_logo)
    var atEnd by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        atEnd = true
        delay(1600)
        onTimeout()
    }

    SplashContent(
        isDark = isDark,
        painter = rememberAnimatedVectorPainter(image, atEnd)
    )
}


@Composable
private fun SplashContent(
    isDark: Boolean,
    painter: Painter
) {
    val backgroundColor = if (isDark) SplashBgDark else SplashBgLight
    val badgeColor = if (isDark) PrimaryDark else Color.White
    val logoColor = if (isDark) SplashLogoDark else SplashBgLight
    val wordmarkColor = if (isDark) PrimaryDark else Color.White

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(badgeColor),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(logoColor),
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.splash_wordmark),
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 32.sp),
                color = wordmarkColor
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun SplashLightPreview() {
    AproveeTheme(darkTheme = false) {
        val image = AnimatedImageVector
            .animatedVectorResource(R.drawable.avd_aprovee_logo)
        @OptIn(ExperimentalAnimationGraphicsApi::class)
        SplashContent(isDark = false, painter = rememberAnimatedVectorPainter(image, true))
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashDarkPreview() {
    AproveeTheme(darkTheme = true) {
        val image = AnimatedImageVector
            .animatedVectorResource(R.drawable.avd_aprovee_logo)
        @OptIn(ExperimentalAnimationGraphicsApi::class)
        SplashContent(isDark = true, painter = rememberAnimatedVectorPainter(image, true))
    }
}