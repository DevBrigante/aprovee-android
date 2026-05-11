package com.aprovee.app.domain.repository

interface AuthRepository {
    suspend fun sendPasswordReset(email: String): Result<Unit>
    suspend fun createAccount(name: String, email: String, password: String): Result<Unit>
}