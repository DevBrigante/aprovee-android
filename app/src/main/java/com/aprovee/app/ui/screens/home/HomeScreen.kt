package com.aprovee.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aprovee.app.R
import com.aprovee.app.ui.components.AproveePrimaryButton
import com.aprovee.app.ui.theme.AproveeTheme

@Composable
private fun FirstTimeBody(
    userName: String,
    onNewClient: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Outlined.Groups,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = stringResource(R.string.home_firsttime_title, userName),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.home_firsttime_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        AproveePrimaryButton(
            text = stringResource(R.string.home_firsttime_cta),
            onClick = onNewClient,
            leadingIcon = Icons.Default.Add
        )
    }
}

@Composable
fun HomeContent(
    state: HomeUiState,
    onNewClient: () -> Unit,
    onBell: () -> Unit,
    onCalendar: () -> Unit,
    onTabSelected: (HomeTab) -> Unit,
    onFab: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(
                modifier = Modifier.statusBarsPadding(),
                onBell = onBell,
                onCalendar = onCalendar
            )
        },
        bottomBar = {
            HomeBottomNav(
                modifier = Modifier.navigationBarsPadding(),
                onTabSelected = onTabSelected,
                onFab = onFab,
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (state.homeState) {
                HomeState.FirstTime -> FirstTimeBody(
                    userName = state.userName,
                    onNewClient = onNewClient,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}


@Composable
fun HomeScreen(
    onNewClient: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeContent(
        state = uiState,
        onNewClient = onNewClient,
        onBell = {},
        onCalendar = {},
        onTabSelected = {},
        onFab = {},
        modifier = modifier
    )
}

@Preview(name = "HomeContent - Light, primeiro acesso", showBackground = true)
@Composable
private fun HomeContentFirstTimeLightPreview() {
    AproveeTheme(darkTheme = false) {
        HomeContent(
            state = HomeUiState(userName = "João"),
            onNewClient = {},
            onBell = {},
            onCalendar = {},
            onTabSelected = {},
            onFab = {}
        )
    }
}

@Preview(name = "HomeContent - Dark, primeiro acesso", showBackground = true)
@Composable
private fun HomeContentFirstTimeDarkPreview() {
    AproveeTheme(darkTheme = true) {
        HomeContent(
            state = HomeUiState(userName = "João"),
            onNewClient = {},
            onBell = {},
            onCalendar = {},
            onTabSelected = {},
            onFab = {}
        )
    }
}