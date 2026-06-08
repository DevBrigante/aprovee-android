package com.aprovee.app.ui.components.illustrations

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aprovee.app.ui.theme.OverdueLight
import com.aprovee.app.ui.theme.PrimaryDark
import com.aprovee.app.ui.theme.PrimaryLight
import com.aprovee.app.ui.theme.TextDark
import com.aprovee.app.ui.theme.TextLight


@Composable
fun IllusFallenServer(
    modifier: Modifier = Modifier, size: Dp = 160.dp, isDark: Boolean = false
) {
    val stroke = if (isDark) TextDark else TextLight
    val accent = if (isDark) PrimaryDark else PrimaryLight
    val error  = OverdueLight

    val infiniteTransition = rememberInfiniteTransition(label = "led-blink")
    val ledAlpha by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 0.2f, animationSpec = infiniteRepeatable(
            animation = tween(700), repeatMode = RepeatMode.Reverse
        ), label = "led-alpha"
    )

    Canvas(modifier = modifier.size(size)) {
        val s = this.size.width / 200f

        drawOval(
            color = accent.copy(alpha = 0.12f),
            topLeft = Offset(38f * s, 166f * s),
            size = Size(124f * s, 12f * s)
        )

        rotate(degrees = -14f, pivot = Offset(100f * s, 110f * s)) {
            drawRoundRect(
                color = stroke,
                topLeft = Offset(58f * s, 46f * s),
                size = Size(84f * s, 128f * s),
                cornerRadius = CornerRadius(8f * s),
                style = Stroke(width = 2f * s)
            )

            drawLine(
                stroke,
                Offset(68f * s, 174f * s),
                Offset(68f * s, 182f * s),
                strokeWidth = 2f * s,
                cap = StrokeCap.Round
            )
            drawLine(
                stroke,
                Offset(132f * s, 174f * s),
                Offset(132f * s, 182f * s),
                strokeWidth = 2f * s,
                cap = StrokeCap.Round
            )

            drawRoundRect(
                stroke,
                Offset(66f * s, 56f * s),
                Size(68f * s, 32f * s),
                CornerRadius(3f * s),
                style = Stroke(1.5f * s)
            )
            drawCircle(accent, 2.5f * s, Offset(74f * s, 72f * s))
            drawLine(
                stroke.copy(alpha = 0.5f),
                Offset(84f * s, 72f * s),
                Offset(104f * s, 72f * s),
                strokeWidth = 1.5f * s,
                cap = StrokeCap.Round
            )
            drawRoundRect(
                stroke.copy(alpha = 0.5f),
                Offset(114f * s, 66f * s),
                Size(14f * s, 12f * s),
                CornerRadius(2f * s),
                style = Stroke(1.5f * s)
            )

            drawRoundRect(
                error,
                Offset(66f * s, 94f * s),
                Size(68f * s, 32f * s),
                CornerRadius(3f * s),
                style = Stroke(1.8f * s)
            )
            drawCircle(error.copy(alpha = ledAlpha), 2.5f * s, Offset(74f * s, 110f * s))
            drawLine(
                error.copy(alpha = 0.55f),
                Offset(84f * s, 110f * s),
                Offset(104f * s, 110f * s),
                strokeWidth = 1.5f * s,
                cap = StrokeCap.Round
            )
            drawRoundRect(
                error.copy(alpha = 0.55f),
                Offset(114f * s, 104f * s),
                Size(14f * s, 12f * s),
                CornerRadius(2f * s),
                style = Stroke(1.5f * s)
            )

            drawRoundRect(
                stroke,
                Offset(66f * s, 132f * s),
                Size(68f * s, 32f * s),
                CornerRadius(3f * s),
                style = Stroke(1.5f * s)
            )
            drawCircle(accent.copy(alpha = 0.45f), 2.5f * s, Offset(74f * s, 148f * s))
            drawLine(
                stroke.copy(alpha = 0.5f),
                Offset(84f * s, 148f * s),
                Offset(104f * s, 148f * s),
                strokeWidth = 1.5f * s,
                cap = StrokeCap.Round
            )
            drawRoundRect(
                stroke.copy(alpha = 0.5f),
                Offset(114f * s, 142f * s),
                Size(14f * s, 12f * s),
                CornerRadius(2f * s),
                style = Stroke(1.5f * s)
            )
        }

        val sparkPath = Path().apply {
            moveTo(158f * s, 64f * s)
            quadraticTo(168f * s, 58f * s, 162f * s, 50f * s)
            quadraticTo(156f * s, 42f * s, 174f * s, 38f * s)
        }
        drawPath(
            sparkPath, error.copy(alpha = 0.85f), style = Stroke(2f * s, cap = StrokeCap.Round)
        )
        drawCircle(error.copy(alpha = 0.85f), 2.5f * s, Offset(178f * s, 34f * s))
        drawCircle(error.copy(alpha = 0.6f), 1.6f * s, Offset(170f * s, 44f * s))

        val zPath = Path().apply {
            moveTo(36f * s, 38f * s)
            lineTo(50f * s, 38f * s)
            lineTo(36f * s, 52f * s)
            lineTo(50f * s, 52f * s)
        }

        drawPath(
            zPath,
            accent.copy(alpha = 0.8f),
            style = Stroke(2f * s, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }

}


@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC)
@Composable
private fun IllusFallenServerLightPreview() {
    IllusFallenServer(isDark = false)
}

@Preview(showBackground = true, backgroundColor = 0xFF1E293B)
@Composable
private fun IllusFallenServerDarkPreview() {
    IllusFallenServer(isDark = true)
}