package com.aprovee.app.data.repository

import com.aprovee.app.domain.model.ErrorType
import com.aprovee.app.domain.model.MaintenanceException
import com.aprovee.app.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import java.io.IOException
import java.net.SocketTimeoutException

class FakeAuthRepositoryImpl : AuthRepository {

    var simulatedError: ErrorType? = null

    override suspend fun sendPasswordReset(email: String): Result<Unit> {
        delay(1500)
        return Result.success(Unit)
    }

    override suspend fun createAccount(
        name: String, email: String, password: String
    ): Result<Unit> {
        delay(1500)
        return when (simulatedError) {
            null -> Result.success(Unit)
            ErrorType.Timeout -> Result.failure(SocketTimeoutException("Simulated timeout"))
            ErrorType.NoConnection -> Result.failure(IOException("Simulated no connection"))
            ErrorType.Maintenance -> Result.failure(MaintenanceException("Simulated maintenance"))
            ErrorType.ServerError -> Result.failure(RuntimeException("Simulated server error"))
        }
    }
}