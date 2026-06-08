package com.aprovee.app.ui.components.illustrations

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.aprovee.app.ui.theme.OverdueLight
import com.aprovee.app.ui.theme.PrimaryDark
import com.aprovee.app.ui.theme.PrimaryLight
import com.aprovee.app.ui.theme.TextDark
import com.aprovee.app.ui.theme.TextLight

@Composable
fun IllusOffline(
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
            topLeft = Offset(44f * s, 167f * s),
            size = Size(112f * s, 10f * s)
        )

        val cloudPath = Path().apply {
            moveTo(58f * s, 96f * s)
            quadraticTo(50f * s, 78f * s, 66f * s, 70f * s)
            quadraticTo(70f * s, 54f * s, 90f * s, 56f * s)
            quadraticTo(100f * s, 42f * s, 118f * s, 50f * s)
            quadraticTo(140f * s, 50f * s, 142f * s, 72f * s)
            quadraticTo(158f * s, 74f * s, 156f * s, 92f * s)
            quadraticTo(156f * s, 108f * s, 138f * s, 108f * s)
            lineTo(70f * s, 108f * s)
            quadraticTo(54f * s, 108f * s, 58f * s, 96f * s)
            close()
        }
        drawPath(
            path = cloudPath,
            color = stroke,
            style = Stroke(width = 2f * s, join = StrokeJoin.Round)
        )

        val arcPath = Path().apply {
            moveTo(76f * s, 140f * s)
            quadraticTo(100f * s, 122f * s, 124f * s, 140f * s)
        }
        drawPath(
            path = arcPath,
            color = stroke,
            style = Stroke(width = 2f * s, cap = StrokeCap.Round)
        )

        val dashedArcPath = Path().apply {
            moveTo(68f * s, 156f * s)
            quadraticTo(100f * s, 130f * s, 132f * s, 156f * s)
        }
        drawPath(
            path = dashedArcPath,
            color = stroke.copy(alpha = 0.5f),
            style = Stroke(
                width = 2f * s,
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(4f * s, 6f * s), 0f
                )
            )
        )

        drawLine(
            color = error,
            start = Offset(42f * s, 44f * s),
            end = Offset(158f * s, 160f * s),
            strokeWidth = 2.5f * s,
            cap = StrokeCap.Round
        )

        drawCircle(
            color = error,
            radius = 3f * s,
            center = Offset(100f * s, 158f * s)
        )
    }
}



@Preview(showBackground = true, backgroundColor = 0xFFF8FAFC)
@Composable
private fun IllusOfflineLightPreview() {
    IllusOffline(isDark = false)
}


@Preview(showBackground = true, backgroundColor = 0xFF1E293B)
@Composable
private fun IllusOfflineDarkPreview() {
    IllusOffline(isDark = true)
}