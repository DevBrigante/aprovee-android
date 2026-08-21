package com.aprovee.app.ui.screens.home

sealed interface HomeState {
    data object FirstTime: HomeState
}