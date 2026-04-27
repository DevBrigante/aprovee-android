package com.aprovee.app.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aprovee.app.R
import com.aprovee.app.ui.components.AproveeIcon
import com.aprovee.app.ui.components.AproveePrimaryButton
import com.aprovee.app.ui.components.AproveeTextField
import com.aprovee.app.ui.theme.AproveeTheme

@Composable
fun ForgotPasswordSheetContent(
    state: ForgotPasswordState,
    email: String,
    emailError: String?,
    onEmailChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    val isLoading = state is ForgotPasswordState.Loading

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
            .padding(bottom = 32.dp),
    ) {

        when (state) {
            is ForgotPasswordState.Input, is ForgotPasswordState.Loading -> {
                Text(
                    text = stringResource(R.string.forgot_password_overline),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.forgot_password_title_input),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.forgot_password_subtitle_input),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(24.dp))

                AproveeTextField(
                    value = email,
                    onValueChange = if(isLoading) {_ ->} else onEmailChange,
                    label = stringResource(R.string.forgot_password_email_label),
                    placeholder = stringResource(R.string.forgot_password_email_placeholder),
                    isError = emailError != null,
                    errorMessage = emailError,
                    keyboardType = KeyboardType.Email
                )
                Spacer(modifier = Modifier.height(24.dp))
                if(isLoading) {
                    Button(
                        onClick = {},
                        enabled = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        )
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    }
                } else {
                    AproveePrimaryButton(
                        text = stringResource(R.string.forgot_password_send_button),
                        onClick = onSendClick,
                        enabled = email.isNotBlank(),
                    )
                }
            }
            is ForgotPasswordState.Sent -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    AproveeIcon(size = 64.dp)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(R.string.forgot_password_overline),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.forgot_password_title_sent),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                                append(stringResource(R.string.forgot_password_body_sent))
                            }
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append(state.email)
                            }
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    OutlinedButton(
                        onClick = onCloseClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.onSurface,
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.forgot_password_close_button),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ForgotPasswordSheetInputPreview() {
    AproveeTheme {
        ForgotPasswordSheetContent(
            state = ForgotPasswordState.Input,
            email = "",
            emailError = null,
            onEmailChange = {},
            onSendClick = {},
            onCloseClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ForgotPasswordSheetInputErrorPreview() {
    AproveeTheme {
        ForgotPasswordSheetContent(
            state = ForgotPasswordState.Input,
            email = "emailerrado",
            emailError = "E-mail inválido",
            onEmailChange = {},
            onSendClick = {},
            onCloseClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ForgotPasswordSheetSentPreview() {
    AproveeTheme {
        ForgotPasswordSheetContent(
            state = ForgotPasswordState.Sent("teste@email.com"),
            email = "",
            emailError = null,
            onEmailChange = {},
            onSendClick = {},
            onCloseClick = {},
        )
    }
}