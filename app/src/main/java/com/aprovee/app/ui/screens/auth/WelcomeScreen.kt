package com.aprovee.app.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aprovee.app.R
import com.aprovee.app.ui.components.AproveeIcon
import com.aprovee.app.ui.theme.AproveeTheme
import com.aprovee.app.ui.theme.BackgroundDark
import com.aprovee.app.ui.theme.Brand
import com.aprovee.app.ui.theme.BrandDark
import com.aprovee.app.ui.theme.BrandLight

@Composable
fun WelcomeScreen(
    onContinue: () -> Unit
) {
    WelcomeContent(
        onContinue = onContinue,
        isDark = isSystemInDarkTheme()
    )
}

@Composable
fun WelcomeContent(
    onContinue: () -> Unit,
    isDark: Boolean = false
) {
    val backgroundColor = if(isDark) BackgroundDark else Brand
    val backgroundTint = if (isDark) BrandDark else BrandLight

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.4f))

            AproveeIcon(
                size = 96.dp,
                backgroundTint = backgroundTint,
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.welcome_title),
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.welcome_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(0.6f))
            Button(
                onClick = onContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = backgroundColor
                )
            ) {
                Text(
                    text = stringResource(R.string.welcome_continue),
                    style = MaterialTheme.typography.titleMedium
                )

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun WelcomeScreenLightPreview() {
    AproveeTheme(darkTheme = false) {
        WelcomeContent(onContinue = {}, isDark = false)
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenDarkPreview() {
    AproveeTheme(darkTheme = true) {
        WelcomeContent(onContinue = {}, isDark = true)
    }
}