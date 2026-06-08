package com.aprovee.app.ui.components.illustrations

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.graphics.Path
import com.aprovee.app.ui.theme.OverdueLight
import com.aprovee.app.ui.theme.PrimaryDark
import com.aprovee.app.ui.theme.PrimaryLight
import com.aprovee.app.ui.theme.TextDark
import com.aprovee.app.ui.theme.TextLight
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun IllusTimeout(
    modifier: Modifier = Modifier,
    size: Dp = 160.dp,
    isDark: Boolean = false
) {
    val stroke = if (isDark) TextDark else TextLight
    val accent = if (isDark) PrimaryDark else PrimaryLight
    val error  = OverdueLight

    Canvas(modifier = modifier.size(size)) {
        val s = this.size.width / 200f

        drawOval(
            color = accent.copy(alpha = 0.12f),
            topLeft = Offset(54f * s, 167f * s),
            size = Size(92f * s, 10f * s)
        )

        val cx = 100f * s
        val cy = 100f * s

        drawCircle(
            color = stroke,
            radius = 54f * s,
            center = Offset(cx, cy),
            style = Stroke(width = 2f * s)
        )

        val hourAngles = listOf(0f, 90f, 180f, 270f)
        for(deg in hourAngles) {
            val rad = Math.toRadians((deg - 90).toDouble())
            val innerR = 46f * s
            val outerR = 52f * s
            drawLine(
                color = stroke,
                start = Offset(
                    cx + innerR * cos(rad).toFloat(),
                    cy + innerR * sin(rad).toFloat()
                ),
                end = Offset(
                    cx + outerR * cos(rad).toFloat(),
                    cy + outerR * sin(rad).toFloat()
                ),
                strokeWidth = 2f * s,
                cap = StrokeCap.Round
            )
        }

        drawLine(
            color = error,
            start = Offset(cx, cy),
            end = Offset(cx, 68f * s),
            strokeWidth = 2.5f * s,
            cap = StrokeCap.Round
        )

        drawLine(
            color = stroke,
            start = Offset(cx, cy),
            end = Offset(128f * s, cy),
            strokeWidth = 2.5f * s,
            cap = StrokeCap.Round
        )

        drawCircle(
            color = error,
            radius = 3f *s,
            center = Offset(cx, cy)
        )

        drawLine(
            color = stroke,
            start = Offset(cx, 40f * s),
            end = Offset(cx, 44f * s),
            strokeWidth = 3f * s,
            cap = StrokeCap.Round
        )

        val swirlPath = Path().apply {
            moveTo(154f * s, 56f * s)
            quadraticTo(168f * s, 60f * s, 168f * s, 74f * s)
        }
        drawPath(
            path = swirlPath,
            color = error.copy(alpha = 0.7f),
            style = Stroke(width = 2f * s, cap = StrokeCap.Round)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC)
@Composable
private fun IllusTimeoutLightPreview() {
    IllusTimeout(isDark = false)
}

@Preview(showBackground = true, backgroundColor = 0xFF1E293B)
@Composable
private fun IllusTimeoutDarkPreview() {
    IllusTimeout(isDark = true)
}