package com.aprovee.app.ui.screens.auth

import androidx.lifecycle.ViewModel
import com.aprovee.app.data.repository.FakeAuthRepository
import com.aprovee.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignupFlowViewModel(
    repository: AuthRepository = FakeAuthRepository()
): ViewModel() {

    private val _uiState = MutableStateFlow<SignupState>(SignupState.Idle)
    val uiState: StateFlow<SignupState> = _uiState.asStateFlow()

    fun submit(name: String, email: String, password: String) {
        // TODO(4.6): chamar repository.createAccount(...) e transicionar estado
    }

    fun onCredentialFlowCompleted() {
        // TODO(4.6): voltar pra Idle após Composable terminar com Credential Manager
        _uiState.update { SignupState.Idle }
    }

    fun onErrorAcknowledged() {
        // TODO(4.6): voltar pra Idle quando o CreateAccount exibir o erro
        _uiState.update { SignupState.Idle }
    }
}