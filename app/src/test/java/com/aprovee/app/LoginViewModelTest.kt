package com.aprovee.app

import com.aprovee.app.domain.repository.AuthRepository
import com.aprovee.app.ui.screens.login.ForgotPasswordState
import com.aprovee.app.ui.screens.login.LoginViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: AuthRepository
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        repository = mockk()
        viewModel = LoginViewModel(repository)
    }

    @Test
    fun onSignClick_whenEmailIsEmpty_setsEmailError() {
        //given
        viewModel.onPasswordChange("12345678")

        //when
        viewModel.onSignClick()

        //then
        assertThat(viewModel.uiState.value.emailError).isNotNull()
        assertThat(viewModel.uiState.value.navigateToHome).isFalse()
    }

    @Test
    fun onSignClick_whenEmailIsInvalid_setsEmailError() {
        //given
        viewModel.onEmailChange("brennogmail.com")
        viewModel.onPasswordChange("12345678")

        //when
        viewModel.onSignClick()

        //then
        assertThat(viewModel.uiState.value.emailError).isNotNull()
    }

    @Test
    fun onSignClick_whenPasswordIsEmpty_setsPasswordError() {
        //given
        viewModel.onEmailChange("brenno@gmail.com")

        //when
        viewModel.onSignClick()

        //then
        assertThat(viewModel.uiState.value.passwordError).isNotNull()
    }

    @Test
    fun onSignClick_whenPasswordTooShort_setsPasswordError() {
        //given
        viewModel.onEmailChange("brenno@gmail.com")
        viewModel.onPasswordChange("1234567")

        //when
        viewModel.onSignClick()

        //then
        assertThat(viewModel.uiState.value.passwordError).isNotNull()
    }

    @Test
    fun onSignClick_whenCredentialsAreValid_setsNavigateToHome() {
        //given
        viewModel.onEmailChange("brenno@gmail.com")
        viewModel.onPasswordChange("12345678")

        //when
        viewModel.onSignClick()

        //then
        assertThat(viewModel.uiState.value.navigateToHome).isTrue()
    }


    @Test
    fun onForgotPasswordSendClick_whenEmailIsBlank_setsError() {
        //given
        viewModel.onForgetPasswordEmailChange("")

        //when
        viewModel.onForgotPasswordSendClick()

        //then
        assertThat(viewModel.uiState.value.forgotPasswordEmailError).isNotNull()
    }

    @Test
    fun onForgotPasswordSendClick_whenEmailIsInvalid_setsError() {
        //given
        viewModel.onForgetPasswordEmailChange("abc")

        //when
        viewModel.onForgotPasswordSendClick()

        //then
        assertThat(viewModel.uiState.value.forgotPasswordEmailError).isNotNull()
    }

    @Test
    fun onForgotPasswordSendClick_whenSendSucceeds_setsSentState() = runTest {
        //given
        coEvery { repository.sendPasswordReset(any()) } returns Result.success(Unit)
        viewModel.onForgetPasswordEmailChange("brenno@gmail.com")

        //when
        viewModel.onForgotPasswordSendClick()

        //then
        assertThat(viewModel.uiState.value.forgotPasswordState)
            .isEqualTo(ForgotPasswordState.Sent("brenno@gmail.com"))
    }

    @Test
    fun onForgotPasswordSendClick_whenSendFails_setsInputStateWithError() = runTest {
        //given
        coEvery { repository.sendPasswordReset(any()) } returns Result.failure(RuntimeException())
        viewModel.onForgetPasswordEmailChange("brenno@gmail.com")

        //when
        viewModel.onForgotPasswordSendClick()

        //then
        assertThat(viewModel.uiState.value.forgotPasswordState)
            .isEqualTo(ForgotPasswordState.Input)
        assertThat(viewModel.uiState.value.forgotPasswordEmailError).isNotNull()
    }
}
