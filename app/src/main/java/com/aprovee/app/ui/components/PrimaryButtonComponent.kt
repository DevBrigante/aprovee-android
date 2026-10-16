package com.aprovee.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aprovee.app.R
import com.aprovee.app.ui.theme.AproveeTheme
import com.aprovee.app.ui.theme.PillShape

@Composable
fun AproveePrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    loadingText: String? = null,
    leadingIcon: Painter? = null
) {
    AproveeButtonBase(
        text = text,
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier,
        enabled = enabled,
        isLoading = isLoading,
        loadingText = loadingText,
        leadingIcon = leadingIcon
    )
}
@Composable
private fun AproveeButtonBase(
    text: String,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    loadingText: String? = null,
    leadingIcon: Painter? = null,
    border: BorderStroke? = null,
    height: Dp = 52.dp
) {
    val isDisabled = !enabled && !isLoading
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(height),
        enabled = enabled && !isLoading,
        shape = PillShape,
        border = if (isDisabled) null else border,
        contentPadding = PaddingValues(horizontal = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = if (isLoading) containerColor.copy(alpha = 0.85f)
            else MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = if (isLoading) contentColor
            else AproveeTheme.colors.textTertiary
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = LocalContentColor.current,
                strokeWidth = 2.5.dp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = loadingText ?: text, style = MaterialTheme.typography.labelLarge)
        } else {
            if (leadingIcon != null) {
                Icon(painter = leadingIcon, contentDescription = null, modifier = Modifier.size(22.dp))
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(text = text, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Composable
private fun PrimaryButtonStates() {
    Column(modifier = Modifier.padding(16.dp)) {
        AproveePrimaryButton(text = "Entrar", onClick = {})
        Spacer(modifier = Modifier.height(12.dp))
        AproveePrimaryButton(
            text = "Tentar novamente",
            onClick = {},
            leadingIcon = painterResource(R.drawable.ic_refresh)
        )
        Spacer(modifier = Modifier.height(12.dp))
        AproveePrimaryButton(text = "Entrar", onClick = {}, isLoading = true, loadingText = "Entrando...")
        Spacer(modifier = Modifier.height(12.dp))
        AproveePrimaryButton(text = "Criar conta", onClick = {}, enabled = false)
    }
}

@Preview(name = "PrimaryButton - Light", showBackground = true)
@Composable
private fun AproveePrimaryButtonLightPreview() {
    AproveeTheme(darkTheme = false) { PrimaryButtonStates() }
}

@Preview(name = "PrimaryButton - Dark", showBackground = true, backgroundColor = 0xFF0B0F0E)
@Composable
private fun AproveePrimaryButtonDarkPreview() {
    AproveeTheme(darkTheme = true) { PrimaryButtonStates() }
}
