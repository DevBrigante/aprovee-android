package com.aprovee.app.ui.components

import android.service.autofill.CustomDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AproveeIcon(
    size: Dp,
    modifier: Modifier = Modifier,
    tint: Color = Color.White,
    backgroundTint: Color = MaterialTheme.colorScheme.primary
) {
    val cornerRadius = size * 0.25f
    val circleSize = size * 0.55f
    val checkSize = size * 0.40f
    val borderWidth = size * 0.04f

    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(cornerRadius))
            .background(color = backgroundTint),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(circleSize)
                .border(
                    width = borderWidth,
                    color = tint,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = "Aprovee icon",
                modifier = Modifier.size(checkSize),
                tint = tint
            )
        }
    }
}

@Preview
@Composable
private fun AproveeIconPreview() {
    AproveeIcon(40.dp, modifier = Modifier, backgroundTint = MaterialTheme.colorScheme.onPrimaryContainer)
}