package com.aprovee.app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aprovee.app.data.repository.FakeOnboardingRepositoryImpl
import com.aprovee.app.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val onboardingRepository: OnboardingRepository = FakeOnboardingRepositoryImpl()
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(userName = "João"))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            onboardingRepository.hasCompletedOnboarding().collect { completed ->
                _uiState.value = _uiState.value.copy(
                    showOnboarding = !completed
                )
            }
        }
    }

    fun onOnboardingCompleted() {
        viewModelScope.launch {
            onboardingRepository.markCompleted()
        }
    }
}