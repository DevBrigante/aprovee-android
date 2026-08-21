package com.aprovee.app.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.aprovee.app.R
import com.aprovee.app.ui.components.AproveeIcon
import com.aprovee.app.ui.theme.AproveeTheme

@Composable
fun HomeTopBar(
    onBell: () -> Unit,
    onCalendar: () -> Unit,
    modifier: Modifier = Modifier,
    unread: Int = 0
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBell) {
                BadgedBox(
                    badge = {
                        if (unread > 0) {
                            Badge(containerColor = MaterialTheme.colorScheme.error)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = stringResource(R.string.home_topbar_notifications)
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                AproveeIcon(size = 24.dp)

                val wordmarkColor = MaterialTheme.colorScheme.onBackground
                val wordmarkAccent = MaterialTheme.colorScheme.primary
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = wordmarkColor)) {
                            append(stringResource(R.string.app_name_appro))
                        }
                        withStyle(SpanStyle(color = wordmarkAccent)) {
                            append(stringResource(R.string.app_name_ee))
                        }
                    },
                    style = MaterialTheme.typography.titleLarge
                )
            }
            IconButton(onClick = onCalendar) {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = stringResource(R.string.home_topbar_calendar)
                )
            }
        }
    }

}

@Preview(name = "HomeTopBar - Light, sem notificações", showBackground = true)
@Composable
private fun HomeTopBarLightPreview() {
    AproveeTheme(darkTheme = false) {
        HomeTopBar(unread = 0, onBell = {}, onCalendar = {})
    }
}

@Preview(name = "HomeTopBar - Dark, sem notificações", showBackground = true)
@Composable
private fun HomeTopBarDarkPreview() {
    AproveeTheme(darkTheme = true) {
        HomeTopBar(unread = 0, onBell = {}, onCalendar = {})
    }
}

@Preview(name = "HomeTopBar - Light, 3 não lidas", showBackground = true)
@Composable
private fun HomeTopBarUnreadPreview() {
    AproveeTheme(darkTheme = false) {
        HomeTopBar(unread = 3, onBell = {}, onCalendar = {})
    }
}

@Preview(name = "HomeTopBar - Dark, 3 não lidas", showBackground = true)
@Composable
private fun HomeTopBarUnreadDarkPreview() {
    AproveeTheme(darkTheme = true) {
        HomeTopBar(unread = 3, onBell = {}, onCalendar = {})
    }
}