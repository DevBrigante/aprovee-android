package com.aprovee.app.ui.screens.auth

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.exceptions.CreateCredentialException
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aprovee.app.R
import com.aprovee.app.ui.theme.AproveeTheme
import com.aprovee.app.ui.theme.BackgroundDark
import com.aprovee.app.ui.theme.BrandDark

@Composable
fun LoadingScreen(
    signupFlowViewModel: SignupFlowViewModel,
    onNavigateToWelcome: () -> Unit,
    onNavigateBackToCreateAccount: (errorMessage: String) -> Unit
) {
    val uiState by signupFlowViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(uiState) {
        when (val current = uiState) {
            is SignupState.Success -> {
                val activity = context as? ComponentActivity
                if(activity != null) {
                    try {
                        val credentialManager = CredentialManager.create(activity)
                        credentialManager.createCredential(
                            context = activity,
                            request = CreatePasswordRequest(
                                id = current.email,
                                password = current.password
                            )
                        )
                    } catch (_: CreateCredentialException) {
                        // Cancelamento ou erro - fluxo não bloqueante
                    }
                }
                signupFlowViewModel.onCredentialFlowCompleted()
                onNavigateToWelcome()
            }
            is SignupState.Error -> {
                signupFlowViewModel.onErrorAcknowledged()
                onNavigateBackToCreateAccount(current.message)
            }
            else -> Unit
        }
    }

    LoadingContent(message = stringResource(R.string.loading_creating_account), isDark = isSystemInDarkTheme())
}

@Composable
fun LoadingContent(message: String, isDark: Boolean = false) {
    val backgroundColor = if (isDark) BackgroundDark else BrandDark

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .windowInsetsPadding(WindowInsets.systemBars),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = Color.White,
                strokeWidth = 3.dp,
                trackColor = Color.White.copy(alpha = 0.2f)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenLightPreview() {
    AproveeTheme(darkTheme = false) {
        LoadingContent(message = stringResource(R.string.loading_creating_account) , isDark = false)
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenDarkPreview() {
    AproveeTheme(darkTheme = true) {
        LoadingContent(message = stringResource(R.string.loading_creating_account), isDark = true)
    }
}