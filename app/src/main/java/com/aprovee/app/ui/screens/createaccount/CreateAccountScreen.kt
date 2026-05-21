package com.aprovee.app.ui.screens.createaccount

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aprovee.app.R
import com.aprovee.app.ui.components.AproveePrimaryButton
import com.aprovee.app.ui.components.AproveeTextField
import com.aprovee.app.ui.theme.AproveeTheme

@Composable
fun CreateAccountScreen(
    onNavigateToHome: () -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: CreateAccountViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.navigateToHome) {
        if(uiState.navigateToHome) {
            onNavigateToHome()
            viewModel.onNavigateToHomeConsumed()
        }
    }

    CreateAccountContent(
        name = uiState.name,
        email = uiState.email,
        password = uiState.password,
        confirmPassword = uiState.confirmPassword,
        nameError = uiState.nameError,
        emailError = uiState.emailError,
        passwordError = uiState.passwordError,
        confirmPasswordError = uiState.confirmPasswordError,
        isLoading = uiState.isLoading,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onCreateAccountClick = viewModel::onCreateAccountClick,
        onBackClick = onNavigateBack,
        onConfirmPasswordFocusLost = viewModel::onConfirmPasswordFocusLost
    )

}

@Composable
private fun CreateAccountContent(
    name: String,
    email: String,
    password: String,
    confirmPassword: String,
    nameError: String?,
    emailError: String?,
    passwordError: String?,
    confirmPasswordError: String?,
    isLoading: Boolean,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onCreateAccountClick: () -> Unit,
    onBackClick: () -> Unit,
    onConfirmPasswordFocusLost: () -> Unit
) {
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmFocusRequester = remember { FocusRequester() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(start = 24.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(12.dp)
                    )
                        .clickable { onBackClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(22.dp)

                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .imePadding()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 28.dp)
                    .padding(top = 8.dp, bottom = 32.dp)
            ) {
                Text(
                    text = stringResource(R.string.create_account_title),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = stringResource(R.string.create_account_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(32.dp))

                AproveeTextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = stringResource(R.string.create_account_name_label),
                    placeholder = stringResource(R.string.create_account_name_placeholder),
                    isError = nameError != null,
                    errorMessage = nameError,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = { emailFocusRequester.requestFocus()})
                )
                Spacer(modifier = Modifier.height(14.dp))

                AproveeTextField(
                    modifier = Modifier.focusRequester(emailFocusRequester),
                    value = email,
                    onValueChange = onEmailChange,
                    label = stringResource(R.string.create_account_email_label),
                    placeholder = stringResource(R.string.create_account_email_placeholder),
                    keyboardType = KeyboardType.Email,
                    isError = emailError != null,
                    errorMessage = emailError,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus()})
                )
                Spacer(modifier = Modifier.height(14.dp))

                AproveeTextField(
                    modifier = Modifier.focusRequester(passwordFocusRequester),
                    value = password,
                    onValueChange = onPasswordChange,
                    label = stringResource(R.string.create_account_password_label),
                    placeholder = stringResource(R.string.create_account_password_placeholder),
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    isError = passwordError != null,
                    errorMessage = passwordError,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = { confirmFocusRequester.requestFocus() })
                )
                Spacer(modifier = Modifier.height(14.dp))

                AproveeTextField(
                    modifier = Modifier
                        .focusRequester(confirmFocusRequester)
                        .onFocusChanged { focusState ->
                            if(!focusState.isFocused) onConfirmPasswordFocusLost()
                        },
                    value = confirmPassword,
                    onValueChange = onConfirmPasswordChange,
                    label = stringResource(R.string.create_account_confirm_label),
                    placeholder = stringResource(R.string.create_account_confirm_placeholder),
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    isError = confirmPasswordError != null,
                    errorMessage = confirmPasswordError,
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(onDone = { onCreateAccountClick() })
                )
                Spacer(modifier = Modifier.height(24.dp))

                AproveePrimaryButton(
                    text = stringResource(R.string.create_account_button),
                    enabled = !isLoading,
                    onClick = onCreateAccountClick
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = stringResource(R.string.create_account_already_have),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { onBackClick() },
                        text = stringResource(R.string.create_account_sign_in),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

            }
        }

    }
}

@Preview(showBackground = true, name = "Light")
@Composable
private fun CreateAccountContentPreviewLight() {
    AproveeTheme(darkTheme = false) {
        CreateAccountContent(
            name = "",
            email = "",
            password = "",
            confirmPassword = "",
            nameError = null,
            emailError = null,
            passwordError = null,
            confirmPasswordError = null,
            isLoading = false,
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onCreateAccountClick = {},
            onBackClick = {},
            onConfirmPasswordFocusLost = {}
        )
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
private fun CreateAccountContentPreviewDark() {
    AproveeTheme(darkTheme = true) {
        CreateAccountContent(
            name = "",
            email = "",
            password = "",
            confirmPassword = "",
            nameError = null,
            emailError = null,
            passwordError = null,
            confirmPasswordError = null,
            isLoading = false,
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onCreateAccountClick = {},
            onBackClick = {},
            onConfirmPasswordFocusLost = {}
        )
    }
}

@Preview(showBackground = true, name = "Dark - Error")
@Composable
private fun CreateAccountContentErrorDark() {
    AproveeTheme (darkTheme = true) {
        CreateAccountContent(
            name = "",
            email = "",
            password = "",
            confirmPassword = "",
            nameError = "Seu nome atingiu o max de caracteres ou campo está vazio",
            emailError = "Esse e-mail já está registrado",
            passwordError = null,
            confirmPasswordError = "As senhas não coincidem",
            isLoading = false,
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onCreateAccountClick = {},
            onBackClick = {},
            onConfirmPasswordFocusLost = {}
        )
    }
}

@Preview(showBackground = true, name = "Light - Error")
@Composable
private fun CreateAccountContentErrorLight() {
    AproveeTheme (darkTheme = false) {
        CreateAccountContent(
            name = "",
            email = "",
            password = "",
            confirmPassword = "",
            nameError = null,
            emailError = "Esse e-mail já está registrado",
            passwordError = null,
            confirmPasswordError = "As senhas não coincidem",
            isLoading = false,
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onCreateAccountClick = {},
            onBackClick = {},
            onConfirmPasswordFocusLost = {}
        )
    }
}

@Preview(showBackground = true, name = "Stateful")
@Composable
private fun CreateAccountScreenStatefulPreview() {
    AproveeTheme {
        CreateAccountScreen(
            onNavigateToHome = {},
            onNavigateBack = {}
        )
    }
}