package com.aprovee.app.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    fun hasCompletedOnboarding(): Flow<Boolean>
    suspend fun markCompleted()
}