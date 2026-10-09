package com.aprovee.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class AproveeColors(
    val textTertiary: Color,
    val card2: Color,
    val brandPressed: Color,
    val paid: Color,
    val paidContainer: Color,
    val pending: Color,
    val pendingContainer: Color,
    val overdue: Color,
    val overdueContainer: Color,
    val info: Color,
    val infoContainer: Color,
)

internal val LightAproveeColors = AproveeColors(
    textTertiary = Text3Light, card2 = Card2Light, brandPressed = BrandPressedLight,
    paid = PaidLight, paidContainer = PaidContainerLight,
    pending = PendingLight, pendingContainer = PendingContainerLight,
    overdue = OverdueLight, overdueContainer = OverdueContainerLight,
    info = InfoLight, infoContainer = InfoContainerLight,
)

internal val DarkAproveeColors = AproveeColors(
    textTertiary = Text3Dark, card2 = Card2Dark, brandPressed = BrandPressedDark,
    paid = PaidDark, paidContainer = PaidContainerDark,
    pending = PendingDark, pendingContainer = PendingContainerDark,
    overdue = OverdueDark, overdueContainer = OverdueContainerDark,
    info = InfoDark, infoContainer = InfoContainerDark,
)

val LocalAproveeColors = staticCompositionLocalOf<AproveeColors> {
    error("AproveeColors não fornecido: Envolva o contéudo em AproveeTheme")
}