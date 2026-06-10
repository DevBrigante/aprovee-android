package com.aprovee.app.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aprovee.app.R
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
    bottomInset: Dp = 0.dp,
) {
    val isLoading = state is ForgotPasswordState.Loading

    val density = LocalDensity.current
    val restingBottomInsets = WindowInsets(
        bottom = with(density) { (32.dp + bottomInset).roundToPx() }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.ime.union(restingBottomInsets))
            .padding(horizontal = 28.dp),
    ) {

        when (state) {
            is ForgotPasswordState.Input, is ForgotPasswordState.Loading -> {
                Spacer(modifier = Modifier.height(8.dp))

                SheetFeatureBadge(icon = Icons.Outlined.Lock, size = 56.dp)

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(R.string.forgot_password_title_input),
                    style = MaterialTheme.typography.headlineLarge,
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
                    onValueChange = if (isLoading) { _ -> } else onEmailChange,
                    leadingIcon = Icons.Outlined.Email,
                    label = stringResource(R.string.forgot_password_email_label),
                    placeholder = stringResource(R.string.forgot_password_email_placeholder),
                    isError = emailError != null,
                    errorMessage = emailError,
                    keyboardType = KeyboardType.Email)
                Spacer(modifier = Modifier.height(24.dp))
                if (isLoading) {
                    Button(
                        onClick = {},
                        enabled = false,
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        )
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp), color = Color.White, strokeWidth = 2.dp
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    SheetFeatureBadge(icon = Icons.Outlined.Email, size = 64.dp)

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(R.string.forgot_password_title_sent),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    val bodyTemplate = stringResource(R.string.forgot_password_body_sent)
                    val bodyParts = bodyTemplate.split("%1\$s")
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                                append(bodyParts[0])
                            }
                            withStyle(
                                SpanStyle(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(state.email)
                            }
                            if (bodyParts.size > 1) {
                                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                                    append(bodyParts[1])
                                }
                            }
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    AproveePrimaryButton(
                        text = stringResource(R.string.forgot_password_close_button),
                        onClick = onCloseClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun SheetFeatureBadge(
    icon: ImageVector, size: Dp, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(size).clip(RoundedCornerShape(size * 0.32f))
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(size * 0.5f)
        )
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