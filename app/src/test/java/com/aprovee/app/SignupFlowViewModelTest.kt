package com.aprovee.app

import com.aprovee.app.domain.model.EmailAlreadyRegisteredException
import com.aprovee.app.domain.model.ErrorType
import com.aprovee.app.domain.model.MaintenanceException
import com.aprovee.app.domain.repository.AuthRepository
import com.aprovee.app.ui.screens.auth.SignupFlowViewModel
import com.aprovee.app.ui.screens.auth.SignupState
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import java.net.SocketTimeoutException


@OptIn(ExperimentalCoroutinesApi::class)
class SignupFlowViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: AuthRepository
    private lateinit var viewModel: SignupFlowViewModel

    @Before
    fun setup() {
        repository = mockk()
        viewModel = SignupFlowViewModel(repository)
    }

    @Test
    fun submit_whenRepositorySucceeds_emitSuccessState() = runTest {
        //given
        coEvery { repository.createAccount(any(), any(), any()) } returns Result.success((Unit))

        //when
        viewModel.submit("Brenno", "brenno@gmail.com", "senha123")

        //then
        assertThat(viewModel.uiState.value).isInstanceOf(SignupState.Success::class.java)
    }

    @Test
    fun submit_whenRepositoryFailsWithTimeout_emitsErrorTimeout() = runTest {
        //given
        coEvery { repository.createAccount(any(), any(), any()) } returns Result.failure(
            SocketTimeoutException()
        )

        //when
        viewModel.submit("Brenno", "brenno@gmail.com", "senha123")

        //then
        assertThat(viewModel.uiState.value).isEqualTo(SignupState.Error(ErrorType.Timeout))
    }

    @Test
    fun submit_whenRepositoryFailsWithIOException_emitsErrorNoConnection() = runTest {
        //given
        coEvery { repository.createAccount(any(), any(), any()) } returns Result.failure(
            IOException()
        )

        //when
        viewModel.submit("Brenno", "brenno@gmail.com", "senha123")

        //then
        assertThat(viewModel.uiState.value).isEqualTo(SignupState.Error(ErrorType.NoConnection))
    }

    @Test
    fun submit_whenRepositoryFailsWithMaintenance_emitsErrorMaintenance() = runTest {
        //given
        coEvery { repository.createAccount(any(), any(), any()) } returns Result.failure(
            MaintenanceException("under maintenance")
        )

        //when
        viewModel.submit("Brenno", "brenno@gmail.com", "senha123")

        //then
        assertThat(viewModel.uiState.value).isEqualTo(SignupState.Error(ErrorType.Maintenance))
    }

    @Test
    fun submit_whenRepositoryFailsWithGenericException_emitsErrorServerError() = runTest {
        //given
        coEvery { repository.createAccount(any(), any(), any()) } returns Result.failure(
            RuntimeException()
        )

        //when
        viewModel.submit("Brenno", "brenno@gmail.com", "senha123")

        //then
        assertThat(viewModel.uiState.value).isEqualTo(SignupState.Error(ErrorType.ServerError))
    }

    @Test
    fun retry_incrementsRetryCount() = runTest {
        //given
        coEvery { repository.createAccount(any(), any(), any()) } returns Result.failure(
            RuntimeException()
        )

        //when
        viewModel.retry()

        //then
        assertThat(viewModel.retryCount).isEqualTo(1)
    }

    @Test
    fun retry_whenCalledThreeTimes_exceedsRetryLimit() = runTest {
        //given
        coEvery { repository.createAccount(any(), any(), any()) } returns Result.failure(
            RuntimeException()
        )

        //when
        repeat(3) { viewModel.retry() }

        //then
        assertThat(viewModel.hasExceededRetryLimit).isTrue()
    }

    @Test
    fun onErrorAcknowledged_resetsRetryCountAndEmitsIdle() = runTest {
        //given
        coEvery { repository.createAccount(any(), any(), any()) } returns Result.failure(
            RuntimeException()
        )
        viewModel.retry()

        //when
        viewModel.onErrorAcknowledged()

        //then
        assertThat(viewModel.retryCount).isEqualTo(0)
        assertThat(viewModel.uiState.value).isEqualTo(SignupState.Idle)
    }

    @Test
    fun hasExceededRetryLimit_whenBelowThreeRetries_isFalse() = runTest {
        //given
        coEvery { repository.createAccount(any(), any(), any()) } returns Result.failure(
            RuntimeException()
        )

        //when
        repeat(2) { viewModel.retry() }

        //then
        assertThat(viewModel.hasExceededRetryLimit).isFalse()
    }

    @Test
    fun submit_whenRepositoryFailsWithEmailAlreadyRegistered_emitsEmailAlreadyRegisteredState() = runTest {
        //given
        coEvery { repository.createAccount(any(), any(), any()) } returns Result.failure(
            EmailAlreadyRegisteredException("email already registered")
        )

        //when
        viewModel.submit("Brenno", "brenno@gmail.com", "senha123")

        //then
        assertThat(viewModel.uiState.value).isEqualTo(SignupState.EmailAlreadyRegistered)
    }

    @Test
    fun onEmailErrorConsumed_emitsIdle() = runTest {
        //given
        coEvery { repository.createAccount(any(), any(), any()) } returns Result.failure(
            EmailAlreadyRegisteredException("email already registered")
        )
        viewModel.submit("Brenno", "brenno@gmail.com", "senha123")

        //when
        viewModel.onEmailErrorConsumed()

        //then
        assertThat(viewModel.uiState.value).isEqualTo(SignupState.Idle)
    }
}