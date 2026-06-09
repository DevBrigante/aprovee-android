package com.aprovee.app.ui.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aprovee.app.ui.theme.AproveeTheme

@Composable
fun AproveePrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(50.dp),
        enabled = enabled && !isLoading,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            disabledContentColor = Color.White.copy(alpha = 0.5f),
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = text, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview
@Composable
private fun AproveePrimaryButtonPreview() {
    AproveeTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            AproveePrimaryButton(
                text = "Entrar", onClick = {})
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            AproveePrimaryButton(
                text = "Carregando", onClick = {}, enabled = false
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            AproveePrimaryButton(
                text = "Carregando", onClick = {}, isLoading = true
            )
        }
    }
}
