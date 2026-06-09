package com.aprovee.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
    val cornerRadius = size * 0.30f

    Box(
        modifier = modifier.size(size).clip(RoundedCornerShape(cornerRadius))
        .background(color = backgroundTint).then(
            if (contentDescription != null) {
            Modifier.semantics { this.contentDescription = contentDescription }
        } else Modifier), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val scale = this.size.width / 100f


            val letterA = Path().apply {
                moveTo(30f * scale, 74f * scale)
                lineTo(50f * scale, 26f * scale)
                lineTo(70f * scale, 74f * scale)
            }

            val check = Path().apply {
                moveTo(37f * scale, 57f * scale)
                lineTo(47f * scale, 66f * scale)
                lineTo(64f * scale, 45f * scale)
            }

            drawPath(
                path = letterA,
                color = tint,
                style = Stroke(width = 10f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round)
            )
            drawPath(
                path = check, color = tint.copy(alpha = 0.92f),
                style = Stroke(width = 9f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round)
            )
        }
    }
}

@Preview
@Composable
private fun AproveeIconPreview() {
    AproveeIcon(80.dp)
}