package com.aprovee.app.ui.screens.home

data class HomeUiState(
    val userName: String = "",
    val homeState: HomeState = HomeState.FirstTime,
    val showOnboarding: Boolean = false
)
