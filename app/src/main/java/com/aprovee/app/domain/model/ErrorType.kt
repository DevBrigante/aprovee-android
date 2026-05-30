package com.aprovee.app.domain.model

sealed class ErrorType {
    data object ServerError : ErrorType()
    data object NoConnection : ErrorType()
    data object Timeout : ErrorType()
    data object Maintenance : ErrorType()
}

fun ErrorType.toRouteParam(): String = when (this) {
    ErrorType.ServerError -> "server_error"
    ErrorType.NoConnection -> "no_connection"
    ErrorType.Timeout -> "timeout"
    ErrorType.Maintenance -> "maintenance"
}

fun String.toErrorType(): ErrorType = when (this) {
    "server_error" -> ErrorType.ServerError
    "no_connection" -> ErrorType.NoConnection
    "timeout" -> ErrorType.Timeout
    "maintenance" -> ErrorType.Maintenance
    else -> ErrorType.ServerError
}