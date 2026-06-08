package com.aprovee.app.ui.components.illustrations

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.aprovee.app.ui.theme.PrimaryDark
import com.aprovee.app.ui.theme.PrimaryLight
import com.aprovee.app.ui.theme.TextDark
import com.aprovee.app.ui.theme.TextLight
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun IllusMaintenance(
    modifier: Modifier = Modifier,
    size: Dp = 160.dp,
    isDark: Boolean = false
) {
    val stroke = if (isDark) TextDark else TextLight
    val accent = if (isDark) PrimaryDark else PrimaryLight

    Canvas(modifier = modifier.size(size)) {
        val s = this.size.width / 200f

        drawOval(
            color = accent.copy(alpha = 0.12f),
            topLeft = Offset(44f * s, 167f * s),
            size = Size(112f * s, 10f * s)
        )

        val gearCx = 100f * s
        val gearCy = 88f * s

        drawCircle(stroke, 20f * s, Offset(gearCx, gearCy), style = Stroke(2f * s))

        drawCircle(stroke, 6f * s, Offset(gearCx, gearCy), style = Stroke(2f * s))

        for (deg in 0 until 360 step 45) {
            val rad = Math.toRadians(deg.toDouble())
            val innerR = 22f * s
            val outerR = 28f * s
            drawLine(
                color = stroke,
                start = Offset(
                    gearCx + innerR * cos(rad).toFloat(),
                    gearCy + innerR * sin(rad).toFloat()
                ),
                end = Offset(
                    gearCx + outerR * cos(rad).toFloat(),
                    gearCy + outerR * sin(rad).toFloat()
                ),
                strokeWidth = 2.5f * s,
                cap = StrokeCap.Round
            )
        }

        rotate(degrees = 38f, pivot = Offset(100f * s, 130f * s)) {
            val wrenchPath = Path().apply {
                moveTo(58f * s, 130f * s)
                lineTo(110f * s, 130f * s)
                lineTo(116f * s, 124f * s)
                lineTo(124f * s, 132f * s)
                lineTo(116f * s, 140f * s)
                lineTo(110f * s, 134f * s)
                lineTo(58f * s, 134f * s)
                close()
            }
            drawPath(
                path = wrenchPath,
                color = accent,
                style = Stroke(2f * s, join = StrokeJoin.Round)
            )
            drawCircle(accent, 8f * s, Offset(54f * s, 132f * s), style = Stroke(2f * s))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC)
@Composable
private fun IllusMaintenanceLightPreview() {
    IllusMaintenance(isDark = false)
}

@Preview(showBackground = true, backgroundColor = 0xFF1E293B)
@Composable
private fun IllusMaintenanceDarkPreview() {
    IllusMaintenance(isDark = true)
}
