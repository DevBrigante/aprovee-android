package com.aprovee.app.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aprovee.app.R
import com.aprovee.app.ui.components.AproveeIcon
import com.aprovee.app.ui.components.AproveePrimaryButton
import com.aprovee.app.ui.components.AproveeTextField
import com.aprovee.app.ui.theme.AproveeTheme

@Composable
fun LoginScreen() {
    LoginContent(
        email = "",
        password = "",
        onEmailChange = {},
        onPasswordChange = {},
        onSignInClick = {}
    )
}

@Composable
private fun LoginContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
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
                keyboardType = KeyboardType.Email
            )
            Spacer(Modifier.height(14.dp))

            AproveeTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = stringResource(R.string.login_password_label),
                placeholder = stringResource(R.string.login_password_placeholder),
                isPassword = true,
                keyboardType = KeyboardType.Password
            )
            Spacer(Modifier.height(42.dp))
            AproveePrimaryButton(
                text = stringResource(R.string.login_sign_in_button),
                onClick = onSignInClick
            )
        }
    }
}

@Preview(name = "Login - Light", showBackground = true)
@Composable
private fun LoginContentLightPreview() {
    AproveeTheme(darkTheme = false) {
        LoginContent(
            email = "brenno@brigante.com",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onSignInClick = {}
        )
    }
}

@Preview(name = "Login - Dark", showBackground = true)
@Composable
private fun LoginContentDarkPreview() {
    AproveeTheme(darkTheme = true) {
        LoginContent(
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onSignInClick = {}
        )
    }
}