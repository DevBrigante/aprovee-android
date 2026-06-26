package com.aprovee.app.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aprovee.app.R
import com.aprovee.app.domain.model.ErrorType
import com.aprovee.app.ui.components.AproveePrimaryButton
import com.aprovee.app.ui.components.illustrations.IllusFallenServer
import com.aprovee.app.ui.components.illustrations.IllusMaintenance
import com.aprovee.app.ui.components.illustrations.IllusOffline
import com.aprovee.app.ui.components.illustrations.IllusTimeout
import com.aprovee.app.ui.theme.AproveeTheme


@Composable
fun ErrorScreen(
    errorType: ErrorType,
    signupFlowViewModel: SignupFlowViewModel,
    onNavigateToLoading: () -> Unit,
    onGoBack: () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val uiState by signupFlowViewModel.uiState.collectAsStateWithLifecycle()
    val retryLimitExceeded = signupFlowViewModel.hasExceededRetryLimit

    LaunchedEffect(uiState) {
        if (uiState is SignupState.Success) onNavigateToLoading()
    }

    val title: String
    val subtitle: String
    val illustration: @Composable () -> Unit

    if (retryLimitExceeded) {
        title = stringResource(R.string.error_retry_limit_title)
        subtitle = stringResource(R.string.error_retry_limit_subtitle)
        illustration = { IllusFallenServer(isDark = isDark) }
    } else {
        when (errorType) {
            ErrorType.ServerError -> {
                title = stringResource(R.string.error_server_title)
                subtitle = stringResource(R.string.error_server_subtitle)
                illustration = { IllusFallenServer(isDark = isDark) }
            }

            ErrorType.NoConnection -> {
                title = stringResource(R.string.error_no_connection_title)
                subtitle = stringResource(R.string.error_no_connection_subtitle)
                illustration = { IllusOffline(isDark = isDark) }
            }

            ErrorType.Timeout -> {
                title = stringResource(R.string.error_timeout_title)
                subtitle = stringResource(R.string.error_timeout_subtitle)
                illustration = { IllusTimeout(isDark = isDark) }
            }

            ErrorType.Maintenance -> {
                title = stringResource(R.string.error_maintenance_title)
                subtitle = stringResource(R.string.error_maintenance_subtitle)
                illustration = { IllusMaintenance(isDark = isDark) }
            }
        }
    }

    ErrorContent(
        title = title,
        subtitle = subtitle,
        illustration = illustration,
        showRetryButton = !retryLimitExceeded,
        retryLimitExceeded = retryLimitExceeded,
        isRetrying = uiState is SignupState.Loading,
        onRetry = { signupFlowViewModel.retry() },
        onGoBack = {
            signupFlowViewModel.onErrorAcknowledged()
            onGoBack()
        })
}

@Composable
fun ErrorContent(
    title: String,
    subtitle: String,
    illustration: @Composable () -> Unit,
    showRetryButton: Boolean,
    retryLimitExceeded: Boolean,
    isRetrying: Boolean,
    onRetry: () -> Unit,
    onGoBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        IconButton(
            onClick = onGoBack,
            modifier = Modifier.align(Alignment.TopStart).padding(start = 8.dp, top = 8.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.error_go_back_button),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            illustration()

            Spacer(modifier = Modifier.height(24.dp))

            if (retryLimitExceeded) {
                Box(
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.error, shape = RoundedCornerShape(12.dp)
                    ).padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.error_retry_limit_badge),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onError
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            if (showRetryButton) {
                AproveePrimaryButton(
                    text = stringResource(R.string.error_retry_button),
                    onClick = onRetry,
                    isLoading = isRetrying,
                    leadingIcon = Icons.Default.Refresh
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            OutlinedButton(
                onClick = onGoBack,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.error_go_back_button),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun ErrorServerLightPreview() {
    AproveeTheme(darkTheme = false) {
        ErrorContent(
            title = "Algo deu errado",
            subtitle = "Não conseguimos criar a sua conta agora. Tente novamente em alguns instantes.",
            illustration = { IllusFallenServer(isDark = false) },
            showRetryButton = true,
            retryLimitExceeded = false,
            onRetry = {},
            onGoBack = {},
            isRetrying = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorServerDarkPreview() {
    AproveeTheme(darkTheme = true) {
        ErrorContent(
            title = "Algo deu errado",
            subtitle = "Não conseguimos criar a sua conta agora. Tente novamente em alguns instantes.",
            illustration = { IllusFallenServer(isDark = true) },
            showRetryButton = true,
            retryLimitExceeded = false,
            onRetry = {},
            onGoBack = {},
            isRetrying = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorNoConnectionLightPreview() {
    AproveeTheme(darkTheme = false) {
        ErrorContent(
            title = "Sem conexão",
            subtitle = "Verifique sua internet e tente novamente.",
            illustration = { IllusOffline(isDark = false) },
            showRetryButton = true,
            retryLimitExceeded = false,
            onRetry = {},
            onGoBack = {},
            isRetrying = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorNoConnectionDarkPreview() {
    AproveeTheme(darkTheme = true) {
        ErrorContent(
            title = "Sem conexão",
            subtitle = "Verifique sua internet e tente novamente.",
            illustration = { IllusOffline(isDark = true) },
            showRetryButton = true,
            retryLimitExceeded = false,
            onRetry = {},
            onGoBack = {},
            isRetrying = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorTimeoutLightPreview() {
    AproveeTheme(darkTheme = false) {
        ErrorContent(
            title = "A solicitação demorou demais",
            subtitle = "O servidor levou tempo demais para responder. Pode ser uma instabilidade temporária.",
            illustration = { IllusTimeout(isDark = false) },
            showRetryButton = true,
            retryLimitExceeded = false,
            onRetry = {},
            onGoBack = {},
            isRetrying = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorTimeoutDarkPreview() {
    AproveeTheme(darkTheme = true) {
        ErrorContent(
            title = "A solicitação demorou demais",
            subtitle = "O servidor levou tempo demais para responder. Pode ser uma instabilidade temporária.",
            illustration = { IllusTimeout(isDark = true) },
            showRetryButton = true,
            retryLimitExceeded = false,
            onRetry = {},
            onGoBack = {},
            isRetrying = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorMaintenanceLightPreview() {
    AproveeTheme(darkTheme = false) {
        ErrorContent(
            title = "Estamos em manutenção",
            subtitle = "Voltamos em alguns minutos. Estamos melhorando o Aprovee para você.",
            illustration = { IllusMaintenance(isDark = false) },
            showRetryButton = true,
            retryLimitExceeded = false,
            onRetry = {},
            onGoBack = {},
            isRetrying = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorMaintenanceDarkPreview() {
    AproveeTheme(darkTheme = true) {
        ErrorContent(
            title = "Estamos em manutenção",
            subtitle = "Voltamos em alguns minutos. Estamos melhorando o Aprovee para você.",
            illustration = { IllusMaintenance(isDark = true) },
            showRetryButton = true,
            retryLimitExceeded = false,
            onRetry = {},
            onGoBack = {},
            isRetrying = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorRetryLimitLightPreview() {
    AproveeTheme(darkTheme = false) {
        ErrorContent(
            title = "Muitas tentativas seguidas",
            subtitle = "Você excedeu o limite de tentativas. Tente novamente mais tarde — já estamos trabalhando pra resolver.",
            illustration = { IllusFallenServer(isDark = false) },
            showRetryButton = false,
            retryLimitExceeded = true,
            onRetry = {},
            onGoBack = {},
            isRetrying = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorRetryLimitDarkPreview() {
    AproveeTheme(darkTheme = true) {
        ErrorContent(
            title = "Muitas tentativas seguidas",
            subtitle = "Você excedeu o limite de tentativas. Tente novamente mais tarde — já estamos trabalhando pra resolver.",
            illustration = { IllusFallenServer(isDark = true) },
            showRetryButton = false,
            retryLimitExceeded = true,
            onRetry = {},
            onGoBack = {},
            isRetrying = false
        )
    }
}