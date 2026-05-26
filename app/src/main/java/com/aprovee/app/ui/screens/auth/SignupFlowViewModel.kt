package com.aprovee.app.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aprovee.app.data.repository.FakeAuthRepository
import com.aprovee.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignupFlowViewModel(
    private val repository: AuthRepository = FakeAuthRepository()
): ViewModel() {

    private val _uiState = MutableStateFlow<SignupState>(SignupState.Idle)
    val uiState: StateFlow<SignupState> = _uiState.asStateFlow()

    fun submit(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { SignupState.Loading }

            repository.createAccount(name, email, password)
                .onSuccess {
                    _uiState.update { SignupState.Success(email, password) }
                }
                .onFailure {
                    _uiState.update { SignupState.Error("Ocorreu um erro ao realizar a criação da sua conta, verifique os dados enviados ou tente novamente") }
                }
        }
    }

    fun onCredentialFlowCompleted() {
        _uiState.update { SignupState.Idle }
    }

    fun onErrorAcknowledged() {
        _uiState.update { SignupState.Idle }
    }
}