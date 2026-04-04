package com.aprovee.app.ui.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aprovee.app.ui.components.AproveeIcon
import com.aprovee.app.ui.theme.AproveeTheme
import com.aprovee.app.ui.theme.BackgroundDark
import com.aprovee.app.ui.theme.Brand
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit
) {
    // 1. Controla se o conteudo é visivel (inicia false para animar)
    var isVisible by remember { mutableStateOf(false) }

    // 2. LaunchedEffect(Unit) roda UMA vez quando o Composable entra na tela
    LaunchedEffect(Unit) {
        isVisible = true // dispara a animação fade-in
        delay(2500L) // espera 2.5s
        onNavigateToLogin() // navega para login
    }

    SplashContent(isVisible = isVisible, isDark = isSystemInDarkTheme())
}


@Composable
fun SplashContent(
    isVisible: Boolean,
    isDark: Boolean = false
) {
    val backgroundColor = if(isDark) BackgroundDark else Brand
    val iconTint = if(isDark) Brand else Color.White

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                // duração da animação
                animationSpec = tween(
                    durationMillis = 800
                )
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Icone
                AproveeIcon(
                    size = 80.dp,
                    tint = iconTint
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                Text(
                    text = "Aprovee",
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
                Text(
                    text = "Suas vendas, sempre sob controle",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Spacer(
                    modifier = Modifier
                        .height(24.dp)
                )
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.White.copy(alpha = 0.4f))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenLightPreview() {
    AproveeTheme(darkTheme = false) {
        SplashContent(isVisible = true, isDark = false) // força visivel no preview
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenDarkPreview() {
    AproveeTheme(darkTheme = true) {
        SplashContent(isVisible = true, isDark = true)
    }
}