package com.aprovee.app.data.repository

import com.aprovee.app.domain.model.EmailAlreadyRegisteredException
import com.aprovee.app.domain.model.ErrorType
import com.aprovee.app.domain.model.InvalidCredentialsException
import com.aprovee.app.domain.model.MaintenanceException
import com.aprovee.app.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import java.io.IOException
import java.net.SocketTimeoutException
import kotlin.time.Duration.Companion.milliseconds

class FakeAuthRepositoryImpl : AuthRepository {

    var simulatedError: ErrorType? = null

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        delay(1500.milliseconds)

        return if(email == "brenno@gmail.com" && password == "12345678") {
            Result.success(Unit)
        } else {
            Result.failure(InvalidCredentialsException("Credenciais incorretas"))
        }
    }

    override suspend fun sendPasswordReset(email: String): Result<Unit> {
        delay(1500.milliseconds)
        return Result.success(Unit)
    }

    override suspend fun createAccount(
        name: String, email: String, password: String
    ): Result<Unit> {
        delay(1500.milliseconds)

        if(email == "brenno@gmail.com") {
            return Result.failure(EmailAlreadyRegisteredException("Email já cadastrado"))
        }

        val forcedError = when (email.trim().lowercase()) {
            "timeout@aprovee.com" -> ErrorType.Timeout
            "offline@aprovee.com" -> ErrorType.NoConnection
            "manutencao@aprovee.com" -> ErrorType.Maintenance
            "erro@aprovee.com" -> ErrorType.ServerError
            else -> null
        }

        return when (forcedError) {
            null -> Result.success(Unit)
            ErrorType.Timeout -> Result.failure(SocketTimeoutException("Simulated timeout"))
            ErrorType.NoConnection -> Result.failure(IOException("Simulated no connection"))
            ErrorType.Maintenance -> Result.failure(MaintenanceException("Simulated maintenance"))
            ErrorType.ServerError -> Result.failure(RuntimeException("Simulated server error"))
        }
    }
}