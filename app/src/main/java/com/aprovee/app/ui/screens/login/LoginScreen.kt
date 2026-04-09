package com.aprovee.app.ui.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aprovee.app.R
import com.aprovee.app.ui.components.AproveeIcon
import com.aprovee.app.ui.components.AproveePrimaryButton
import com.aprovee.app.ui.components.AproveeTextField
import com.aprovee.app.ui.theme.AproveeTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginContent(
        email = uiState.email,
        password = uiState.password,
        emailError = uiState.emailError,
        passwordError = uiState.passwordError,
        isRememberMeChecked = uiState.isRememberMeChecked,
        onRememberMeChange = viewModel::onRememberMeChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = viewModel::onSignClick,
        onForgotPasswordClick = viewModel::onForgetPasswordClick
    )
}

@Composable
private fun LoginContent(
    email: String,
    password: String,
    emailError: String?,
    passwordError: String?,
    isRememberMeChecked: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRememberMeChange: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignInClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 28.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AproveeIcon(size = 72.dp)
            Spacer(Modifier.height(14.dp))

            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.login_subtitle),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(32.dp))

            AproveeTextField(
                value = email,
                onValueChange = onEmailChange,
                label = stringResource(R.string.login_email_label),
                placeholder = stringResource(R.string.login_email_placeholder),
                keyboardType = KeyboardType.Email,
                isError = emailError != null,
                errorMessage = emailError
            )
            Spacer(Modifier.height(14.dp))

            AproveeTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = stringResource(R.string.login_password_label),
                placeholder = stringResource(R.string.login_password_placeholder),
                isPassword = true,
                keyboardType = KeyboardType.Password,
                isError = passwordError != null,
                errorMessage = passwordError
            )
            Spacer(Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = (6).dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            role = Role.Button
                        ) { onRememberMeChange() }
                ) {
                    CompositionLocalProvider(
                        LocalMinimumInteractiveComponentSize provides Dp.Unspecified
                    ) {
                        Checkbox(
                            checked = isRememberMeChecked,
                            onCheckedChange = null,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.login_remember_me),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onForgotPasswordClick() },
                    text = stringResource(R.string.login_forgot_password),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )

            }
            Spacer(modifier = Modifier.height(20.dp))

            AproveePrimaryButton(
                text = stringResource(R.string.login_sign_in_button),
                onClick = onSignInClick
            )
        }
    }
}

@Preview(name = "Login - Light", showBackground = true, showSystemUi = true)
@Composable
private fun LoginContentLightPreview() {
    AproveeTheme(darkTheme = false) {
        LoginContent(
            email = "brenno@brigante.com",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onSignInClick = {},
            emailError = null,
            passwordError = null,
            isRememberMeChecked = false,
            onRememberMeChange = {},
            onForgotPasswordClick = {}
        )
    }
}

@Preview(name = "Login - Dark", showBackground = true, showSystemUi = true)
@Composable
private fun LoginContentDarkPreview() {
    AproveeTheme(darkTheme = true) {
        LoginContent(
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onSignInClick = {},
            emailError = null,
            passwordError = null,
            isRememberMeChecked = false,
            onRememberMeChange = {},
            onForgotPasswordClick = {}
        )
    }
}

@Preview(name = "Login - Light com Erro", showBackground = true)
@Composable
private fun LoginContentErrorPreview() {
    AproveeTheme(darkTheme = false) {
        LoginContent(
            email = "email@invalido",
            password = "123",
            emailError = "E-mail inválido",
            passwordError = "A senha deve ter no mínimo 8 caracteres",
            onEmailChange = {},
            onPasswordChange = {},
            onSignInClick = {},
            isRememberMeChecked = false,
            onRememberMeChange = {},
            onForgotPasswordClick = {}
        )
    }
}

@Preview(name = "Login - Dark com Erro", showBackground = true)
@Composable
private fun LoginContentErrorDarkPreview() {
    AproveeTheme(darkTheme = true) {
        LoginContent(
            email = "email@invalido",
            password = "123",
            emailError = "E-mail inválido",
            passwordError = "A senha deve ter no mínimo 8 caracteres",
            onEmailChange = {},
            onPasswordChange = {},
            onSignInClick = {},
            isRememberMeChecked = false,
            onRememberMeChange = {},
            onForgotPasswordClick = {}
        )
    }
}

@Preview(name = "Login - Stateful (interativo)", showBackground = true)
@Composable
private fun LoginScreenStatefulPreview() {
    AproveeTheme {
        LoginScreen()
    }
}