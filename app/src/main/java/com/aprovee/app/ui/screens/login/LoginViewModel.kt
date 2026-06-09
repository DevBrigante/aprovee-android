package com.aprovee.app.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aprovee.app.data.repository.FakeAuthRepositoryImpl
import com.aprovee.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository = FakeAuthRepositoryImpl()
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, emailError = null, credentialError = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, passwordError = null, credentialError = null) }
    }

    fun onForgetPasswordClick() {
        _uiState.update { it.copy(showForgotPasswordSheet = true) }
    }

    fun onForgetPasswordEmailChange(value: String) {
        _uiState.update { it.copy(forgotPasswordEmail = value, forgotPasswordEmailError = null) }
    }

    fun onForgotPasswordSendClick() {
        val email = _uiState.value.forgotPasswordEmail
        val emailError = when {
            email.isBlank() -> "Informe seu e-mail"
            !email.contains("@") || !email.contains(".") -> "E-mail inválido"
            else -> null
        }
        if (emailError != null) {
            _uiState.update { it.copy(forgotPasswordEmailError = emailError) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(forgotPasswordState = ForgotPasswordState.Loading) }

            authRepository.sendPasswordReset(email).onSuccess {
                    _uiState.update { it.copy(forgotPasswordState = ForgotPasswordState.Sent(email)) }
                }.onFailure {
                    _uiState.update {
                        it.copy(
                            forgotPasswordState = ForgotPasswordState.Input,
                            forgotPasswordEmailError = "Não foi possível enviar. Tente novamente"
                        )
                    }
                }
        }
    }

    fun onDismissForgotPasswordSheet() {
        _uiState.update {
            it.copy(
                showForgotPasswordSheet = false,
                forgotPasswordEmail = "",
                forgotPasswordEmailError = null,
                forgotPasswordState = ForgotPasswordState.Input
            )
        }
    }

    fun onCreateAccountClick() {
        _uiState.update { it.copy(navigateToCreateAccount = true) }
    }

    fun onNavigateToCreateAccountConsumed() {
        _uiState.update { it.copy(navigateToCreateAccount = false) }
    }

    fun onNavigateToHomeConsumed() {
        _uiState.update { it.copy(navigateToHome = false) }
    }

    fun onSignClick() {
        val currentState = _uiState.value

        val emailError = when {
            currentState.email.isEmpty() -> "Campo obrigatório"
            !currentState.email.contains("@") || !currentState.email.contains(".") -> "E-mail inválido"
            else -> null
        }

        val passwordError = when {
            currentState.password.isEmpty() -> "Campo obrigatório"
            currentState.password.length < 8 -> "A senha deve ter no mínimo 8 caracteres"
            else -> null
        }

        if (emailError != null || passwordError != null) {
            _uiState.update { it.copy(emailError = emailError, passwordError = passwordError) }
            return
        }

        if(currentState.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, credentialError = null) }

            authRepository.login(currentState.email, currentState.password).onSuccess {
                    _uiState.update { it.copy(isLoading = false, navigateToHome = true) }
                }.onFailure {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            credentialError = "E-mail ou senha incorretos. Tente novamente."
                        )
                    }
                }
        }
    }
}