package com.aprovee.app.data.repository

import com.aprovee.app.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeOnboardingRepositoryImpl(
    initialCompleted: Boolean = false
) : OnboardingRepository {
    private val _hasCompletedOnboarding = MutableStateFlow(initialCompleted)

    override fun hasCompletedOnboarding(): Flow<Boolean> =
        _hasCompletedOnboarding.asStateFlow()


    override suspend fun markCompleted() {
        _hasCompletedOnboarding.value = true
    }
}