package com.aprovee.app.ui.screens.createaccount

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateAccountViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(CreateAccountUiState())
    val uiState: StateFlow<CreateAccountUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.update { it.copy(name = value, nameError = null) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, emailError = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, passwordError = null) }
    }

    fun onConfirmPasswordChange(value: String) {
        _uiState.update { it.copy(confirmPassword = value, confirmPasswordError = null) }
    }

    fun onConfirmPasswordFocusLost() {
        val s = uiState.value
        if (s.confirmPassword.isEmpty()) return
        val error = if (s.confirmPassword != s.password) "As senhas não coincidem" else null
        _uiState.update { it.copy(confirmPasswordError = error) }
    }

    fun onCreateAccountClick() {
        val s = _uiState.value
        val nameError: String? = when {
            s.name.isBlank() -> "O campo está vazio, por favor digite um nome válido"
            s.name.length > 50 -> "Nome muito longo (máximo 50 caracteres), por favor digite um nome válido"
            else -> null
        }
        val emailError: String? = if(!s.email.contains("@") || !s.email.contains(".")) "E-mail inválido" else null
        val passwordError: String? = if(s.password.length < 8) "A senha deve ter no mínimo 8 caracteres" else null
        val confirmPasswordError: String? = if(s.confirmPassword != s.password) "As senhas não coincidem" else null

        _uiState.update { it.copy(
            nameError = nameError,
            emailError = emailError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError
        ) }

        val hasError = listOf(nameError, emailError, passwordError, confirmPasswordError).any { it!=null }
        if (hasError) return

        _uiState.update { it.copy(submitRequested = true) }
    }

    fun onSubmitConsumed() {
        _uiState.update { it.copy(submitRequested = false) }
    }

    fun onEmailAlreadyRegistered() {
        _uiState.update { it.copy(emailError = "Este e-mail já está cadastrado") }
    }
}