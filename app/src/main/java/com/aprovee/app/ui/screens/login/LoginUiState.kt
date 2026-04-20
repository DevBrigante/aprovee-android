package com.aprovee.app.ui.screens.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isRememberMeChecked: Boolean = false,
    val isLoading: Boolean = false,
    val showForgotPasswordSheet: Boolean = false,
    val navigateToHome: Boolean = false,
    val navigateToCreateAccount: Boolean = false
)
