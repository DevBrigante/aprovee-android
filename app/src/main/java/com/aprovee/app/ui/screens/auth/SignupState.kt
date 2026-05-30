package com.aprovee.app.ui.screens.auth

import com.aprovee.app.domain.model.ErrorType

sealed class SignupState {
    data object Idle: SignupState()
    data object Loading: SignupState()
    data class Success(val email: String, val password: String): SignupState()
    data class Error(val type: ErrorType): SignupState()
}