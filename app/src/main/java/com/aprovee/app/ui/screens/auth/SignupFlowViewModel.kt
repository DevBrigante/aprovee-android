package com.aprovee.app.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aprovee.app.data.repository.FakeAuthRepositoryImpl
import com.aprovee.app.domain.model.EmailAlreadyRegisteredException
import com.aprovee.app.domain.model.ErrorType
import com.aprovee.app.domain.model.MaintenanceException
import com.aprovee.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

class SignupFlowViewModel(
    private val repository: AuthRepository = FakeAuthRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<SignupState>(SignupState.Idle)
    val uiState: StateFlow<SignupState> = _uiState.asStateFlow()

    private var lastSubmitName: String = ""
    private var lastSubmitEmail: String = ""
    private var lastSubmitPassword: String = ""

    private var _retryCount = 0
    val retryCount: Int get() = _retryCount
    val hasExceededRetryLimit: Boolean get() = _retryCount >= 3

    fun submit(name: String, email: String, password: String) {
        lastSubmitName = name
        lastSubmitEmail = email
        lastSubmitPassword = password

        viewModelScope.launch {
            _uiState.update { SignupState.Loading }

            repository.createAccount(name, email, password).onSuccess {
                _retryCount = 0
                _uiState.update { SignupState.Success(email, password) }
            }.onFailure { throwable ->
                if (throwable is EmailAlreadyRegisteredException) {
                    _uiState.update { SignupState.EmailAlreadyRegistered }
                } else {
                    val errorType = when (throwable) {
                        is SocketTimeoutException -> ErrorType.Timeout
                        is IOException -> ErrorType.NoConnection
                        is MaintenanceException -> ErrorType.Maintenance
                        else -> ErrorType.ServerError
                    }
                    _uiState.update { SignupState.Error(errorType) }
                }
            }
        }
    }

    fun onEmailErrorConsumed() {
        _uiState.update { SignupState.Idle }
    }

    fun retry() {
        _retryCount++
        submit(lastSubmitName, lastSubmitEmail, lastSubmitPassword)
    }

    fun onCredentialFlowCompleted() {
        _uiState.update { SignupState.Idle }
    }

    fun onErrorAcknowledged() {
        _retryCount = 0
        _uiState.update { SignupState.Idle }
    }
}