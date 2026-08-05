package com.aprovee.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aprovee.app.R
import com.aprovee.app.ui.theme.AproveeTheme

enum class HomeTab { Home, Clients, Revenue, Profile }

@Composable
fun HomeBottomNav(
    onTabSelected: (HomeTab) -> Unit,
    onFab: () -> Unit,
    modifier: Modifier = Modifier,
    activeTab: HomeTab = HomeTab.Home
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Column {
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeBottomNavItem(
                    icon = Icons.Outlined.Home,
                    label = stringResource(R.string.home_tab_home),
                    isActive = activeTab == HomeTab.Home,
                    onClick = { onTabSelected(HomeTab.Home) },
                    modifier = Modifier.weight(1f)
                )
                HomeBottomNavItem(
                    icon = Icons.Outlined.Groups,
                    label = stringResource(R.string.home_tab_clients),
                    isActive = activeTab == HomeTab.Clients,
                    onClick = { onTabSelected(HomeTab.Clients) },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(58.dp))
                HomeBottomNavItem(
                    icon = Icons.Outlined.AttachMoney,
                    label = stringResource(R.string.home_tab_revenue),
                    isActive = activeTab == HomeTab.Revenue,
                    onClick = { onTabSelected(HomeTab.Revenue) },
                    modifier = Modifier.weight(1f)
                )
                HomeBottomNavItem(
                    icon = Icons.Outlined.Person,
                    label = stringResource(R.string.home_tab_profile),
                    isActive = activeTab == HomeTab.Profile,
                    onClick = { onTabSelected(HomeTab.Profile) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Box(
            modifier = Modifier
                .size(58.dp)
                .clickable { onFab() }
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(20.dp)
                    )
                .border(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                )
                .align(Alignment.TopCenter)
                .offset(y = (-22).dp)
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center),
                imageVector = Icons.Default.Add,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = stringResource(R.string.home_fab_new_client),
            )
        }
    }
}

@Composable
private fun HomeBottomNavItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    isActive: Boolean,
    onClick: () -> Unit
) {
    val color = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant

    Column(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
            ),
            color = color
        )
    }
}



@Preview(name = "HomeBottomNav - Light", showBackground = true, heightDp = 100)
@Composable
private fun HomeBottomNavLightPreview() {
    AproveeTheme(darkTheme = false) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            HomeBottomNav(onTabSelected = {}, onFab = {}, activeTab = HomeTab.Home)
        }
    }
}

@Preview(name = "HomeBottomNav - Dark", showBackground = true, heightDp = 100)
@Composable
private fun HomeBottomNavDarkPreview() {
    AproveeTheme(darkTheme = true) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            HomeBottomNav(onTabSelected = {}, onFab = {}, activeTab = HomeTab.Clients)
        }
    }
}
