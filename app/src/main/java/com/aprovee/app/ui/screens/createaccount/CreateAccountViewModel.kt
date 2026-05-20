package com.aprovee.app.ui.screens.createaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aprovee.app.data.repository.FakeAuthRepository
import com.aprovee.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateAccountViewModel(
    private val repository: AuthRepository = FakeAuthRepository()
): ViewModel() {
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
        val error = if (s.confirmPassword != s.password) "As senhas não condizem" else null
        _uiState.update { it.copy(confirmPasswordError = error) }
    }

    fun onCreateAccountClick() {
        val s = _uiState.value
        val nameError: String? = if(s.name.isBlank() || s.name.length > 20 ) "Seu nome atingiu o max de caracteres ou campo está vazio" else null
        val emailError: String? = if(!s.email.contains("@") || !s.email.contains(".")) "E-mail inválido" else null
        val passwordError: String? = if(s.password.length < 8) "A senha deve ter no mínimo 8 caracteres" else null
        val confirmPasswordError: String? = if(s.confirmPassword != s.password) "As senhas não condizem" else null

        _uiState.update { it.copy(
            nameError = nameError,
            emailError = emailError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError
        ) }

        val hasError = listOf(nameError, emailError, passwordError, confirmPasswordError).any { it!=null }
        if (hasError) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.createAccount(s.name, s.email, s.password)
            _uiState.update { it.copy(isLoading = false, navigateToHome = true, isSuccess = true) }
        }
    }

    fun onNavigateToHomeConsumed() {
        _uiState.update { it.copy(navigateToHome = false) }
    }
}