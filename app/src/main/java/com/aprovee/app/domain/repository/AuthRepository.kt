package com.aprovee.app.domain.repository

interface AuthRepository {
    suspend fun sendPasswordReset(email: String): Result<Unit>
}