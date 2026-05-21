package com.aprovee.app.ui.screens.auth

sealed class SignupState {
    data object Idle: SignupState()
    data object Loading: SignupState()
    data class Success(val email: String, val password: String): SignupState()
    data class Error(val message: String): SignupState()
}