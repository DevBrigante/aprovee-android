package com.aprovee.app.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aprovee.app.R
import com.aprovee.app.ui.components.AproveeIcon
import com.aprovee.app.ui.components.AproveePrimaryButton
import com.aprovee.app.ui.components.AproveeTextField
import com.aprovee.app.ui.theme.AproveeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToCreateAccount: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(uiState.navigateToHome) {
        if (uiState.navigateToHome) {
            onNavigateToHome()
            viewModel.onNavigateToHomeConsumed()
        }
    }

    LaunchedEffect(uiState.navigateToCreateAccount) {
        if (uiState.navigateToCreateAccount) {
            onNavigateToCreateAccount()
            viewModel.onNavigateToCreateAccountConsumed()
        }
    }

    LoginContent(
        email = uiState.email,
        password = uiState.password,
        emailError = uiState.emailError,
        passwordError = uiState.passwordError,
        credentialError = uiState.credentialError,
        isLoading = uiState.isLoading,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = viewModel::onSignClick,
        onForgotPasswordClick = viewModel::onForgetPasswordClick,
        onCreateAccountClick = viewModel::onCreateAccountClick,
    )

    val sheetBottomInset = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    if (uiState.showForgotPasswordSheet) {
        ModalBottomSheet(
            onDismissRequest = viewModel::onDismissForgotPasswordSheet,
            sheetState = sheetState,
            contentWindowInsets = { WindowInsets(0) },
        ) {
            ForgotPasswordSheetContent(
                state = uiState.forgotPasswordState,
                email = uiState.forgotPasswordEmail,
                emailError = uiState.forgotPasswordEmailError,
                bottomInset = sheetBottomInset,
                onEmailChange = viewModel::onForgetPasswordEmailChange,
                onSendClick = viewModel::onForgotPasswordSendClick,
                onCloseClick = viewModel::onDismissForgotPasswordSheet
            )
        }
    }
}

@Composable
private fun LoginContent(
    email: String,
    password: String,
    emailError: String?,
    passwordError: String?,
    credentialError: String?,
    isLoading: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignInClick: () -> Unit,
    onCreateAccountClick: () -> Unit
) {
    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
                .verticalScroll(rememberScrollState())
                .windowInsetsPadding(WindowInsets.systemBars.union(WindowInsets.ime))
                .padding(horizontal = 28.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AproveeIcon(size = 72.dp)
            Spacer(Modifier.height(14.dp))

            val wordmarkColor = MaterialTheme.colorScheme.onBackground
            val wordmarkAccent = MaterialTheme.colorScheme.primary
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = wordmarkColor)) { append(stringResource(R.string.app_name_appro)) }
                    withStyle(SpanStyle(color = wordmarkAccent)) { append(stringResource(R.string.app_name_ee)) }
                },
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.login_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(32.dp))

            AproveeTextField(
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }),
                value = email,
                onValueChange = onEmailChange,
                leadingIcon = Icons.Outlined.Email,
                label = stringResource(R.string.login_email_label),
                placeholder = stringResource(R.string.login_email_placeholder),
                keyboardType = KeyboardType.Email,
                isError = emailError != null,
                errorMessage = emailError
            )
            Spacer(Modifier.height(14.dp))

            AproveeTextField(
                modifier = Modifier.focusRequester(passwordFocusRequester),
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                value = password,
                onValueChange = onPasswordChange,
                leadingIcon = Icons.Outlined.Lock,
                leadingIconSize = 24.dp,
                label = stringResource(R.string.login_password_label),
                placeholder = stringResource(R.string.login_password_placeholder),
                isPassword = true,
                keyboardType = KeyboardType.Password,
                isError = passwordError != null,
                errorMessage = passwordError
            )

            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        role = Role.Button
                    ) { onForgotPasswordClick() }
                    .padding(vertical = 8.dp),
                text = stringResource(R.string.login_forgot_password),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))

            if (credentialError != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.errorContainer)
                        .padding(horizontal = 13.dp, vertical = 11.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Outlined.WarningAmber,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = credentialError,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            AproveePrimaryButton(
                text = stringResource(R.string.login_sign_in_button),
                onClick = onSignInClick,
                isLoading = isLoading
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = stringResource(R.string.login_or_divider),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = stringResource(R.string.login_google_icon_description),
                    modifier = Modifier.size(30.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = stringResource(R.string.login_google_sign_in),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600),
                )
            }
            Spacer(modifier = Modifier.height(64.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.login_no_account),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { onCreateAccountClick() },
                    text = stringResource(R.string.login_create_account),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
            }
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
            onForgotPasswordClick = {},
            onCreateAccountClick = {},
            isLoading = false,
            credentialError = null
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
            isLoading = false,
            credentialError = null,
            onForgotPasswordClick = {},
            onCreateAccountClick = {}
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
            isLoading = false,
            credentialError = "Login ou senha incorretos. Tente novamente.",
            onForgotPasswordClick = {},
            onCreateAccountClick = {}
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
            isLoading = false,
            credentialError = "Login ou senha incorretos. Tente novamente.",
            onEmailChange = {},
            onPasswordChange = {},
            onSignInClick = {},
            onForgotPasswordClick = {},
            onCreateAccountClick = {}
        )
    }
}