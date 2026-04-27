package com.aprovee.app.data.repository

import com.aprovee.app.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class FakeAuthRepository: AuthRepository {
    override suspend fun sendPasswordReset(email: String): Result<Unit> {
        delay(1500)
        return Result.success(Unit)
    }
}