package com.aprovee.app.ui.screens.login

sealed class ForgotPasswordState {
    data object Input: ForgotPasswordState()
    data object Loading: ForgotPasswordState()
    data class Sent(val email: String): ForgotPasswordState()
}