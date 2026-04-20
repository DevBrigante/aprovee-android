package com.aprovee.app.ui.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, emailError = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, passwordError = null) }
    }

    fun onRememberMeChange() {
        _uiState.update { it.copy(isRememberMeChecked = !it.isRememberMeChecked) }
    }

    fun onForgetPasswordClick() {
        _uiState.update { it.copy(showForgotPasswordSheet = true) }
    }

    fun onCreateAccountClick() {
        _uiState.update { it.copy(navigateToCreateAccount = true) }
    }

    fun onNavigateToCreateAccountConsumed() {
        _uiState.update { it.copy(navigateToCreateAccount = false) }
    }

    fun onDismissForgotPasswordSheet() {
        _uiState.update { it.copy(showForgotPasswordSheet = false) }
    }

    fun onNavigateToHomeConsumed() {
        _uiState.update { it.copy(navigateToHome = false) }
    }

    fun onSignClick() {
        val currentState = _uiState.value

        val emailError = when {
            currentState.email.isEmpty() -> "Informe seu e-mail"
            !currentState.email.contains("@") || !currentState.email.contains(".") -> "E-mail inválido"
            else -> null
        }

        val passwordError = when {
            currentState.password.isEmpty() -> "Informe sua senha"
            currentState.password.length < 8 -> "A senha deve ter no mínimo 8 caracteres"
            else -> null
        }

        if(emailError != null || passwordError != null) {
            _uiState.update { it.copy(emailError = emailError, passwordError = passwordError) }
        } else {
            _uiState.update { it.copy(navigateToHome = true) }
        }
    }
}