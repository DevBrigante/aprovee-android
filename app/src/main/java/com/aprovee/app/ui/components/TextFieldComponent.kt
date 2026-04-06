package com.aprovee.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aprovee.app.ui.theme.AproveeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.res.stringResource
import com.aprovee.app.R

@Composable
fun AproveeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(
            modifier = Modifier
                .height(6.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            isError = isError,
            visualTransformation = if(isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                if(isPassword) {
                    IconButton(
                        onClick = {passwordVisible = !passwordVisible}
                    ) {
                        Icon(
                            imageVector = if(passwordVisible) {
                                Icons.Outlined.Visibility
                            } else {
                                Icons.Outlined.VisibilityOff
                            },
                            contentDescription = stringResource(R.string.toggle_password_visibility)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                errorBorderColor = MaterialTheme.colorScheme.error,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        if(isError && errorMessage != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = null,
                    modifier = Modifier
                        .size(13.dp),
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun AproveeTextFieldPreview() {
    AproveeTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            AproveeTextField(
                value = "",
                onValueChange = {},
                label = "E-mail",
                placeholder = "seu@email.com"
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            AproveeTextField(
                value = "email@errado",
                onValueChange = {},
                label = "E-mail",
                isError = true,
                errorMessage = "Esse e-mail não foi encontrado"
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            AproveeTextField(
                value = "",
                onValueChange = {},
                label = "Senha",
                placeholder = "min. 8 caracteres",
                isPassword = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AproveeTextFieldDarkPreview() {
    AproveeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            AproveeTextField(
                value = "",
                onValueChange = {},
                label = "E-mail",
                placeholder = "seu@email.com"
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            AproveeTextField(
                value = "email@errado",
                onValueChange = {},
                label = "E-mail",
                isError = true,
                errorMessage = "Esse e-mail não foi encontrado"
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            AproveeTextField(
                value = "225617",
                onValueChange = {},
                label = "Senha",
                placeholder = "min. 8 caracteres",
                isPassword = true
            )
        }
    }
}