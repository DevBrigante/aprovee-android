package com.aprovee.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AproveeIcon(
    size: Dp,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.White,
    backgroundTint: Color = MaterialTheme.colorScheme.primary
) {
    val cornerRadius = size * 0.25f
    val markScale = 0.55f

    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(cornerRadius))
            .background(color = backgroundTint)
            .then(
                if (contentDescription != null) {
                    Modifier.semantics { this.contentDescription = contentDescription }
                } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(size * markScale)
        ) {
            val scale = this.size.width / 48f
            val stroke = Stroke(
                width = 6f * scale,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )

            val leftLeg = Path().apply {
                moveTo(8f * scale, 42f * scale)
                lineTo(24f * scale, 6f * scale)
            }
            val rightLegWithHook = Path().apply {
                moveTo(24f * scale, 6f * scale)
                lineTo(36f * scale, 30f * scale)
                lineTo(29f * scale, 30f * scale)
                lineTo(40f * scale, 18f * scale)
            }
            drawPath(leftLeg, color = tint, style = stroke)
            drawPath(rightLegWithHook, color = tint, style = stroke)
        }
    }
}

@Preview
@Composable
private fun AproveeIconPreview() {
    AproveeIcon(80.dp)
}