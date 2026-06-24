package com.aprovee.app

import com.aprovee.app.ui.screens.createaccount.CreateAccountViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CreateAccountViewModelTest {
    private lateinit var viewModel: CreateAccountViewModel

    @Before
    fun setup() {
        viewModel = CreateAccountViewModel()
    }

    @Test
    fun onCreateAccountClick_whenAllFieldsValid_setsSubmitRequested() {
        //given
        viewModel.onNameChange("Brenno")
        viewModel.onEmailChange("brenno@gmail.com")
        viewModel.onPasswordChange("12345678")
        viewModel.onConfirmPasswordChange("12345678")

        //when
        viewModel.onCreateAccountClick()

        //then
        assertThat(viewModel.uiState.value.submitRequested).isTrue()
    }

    @Test
    fun onCreateAccountClick_whenNameIsBlank_setsNameError() {
        //given
        viewModel.onNameChange("")
        viewModel.onEmailChange("brenno@gmail.com")
        viewModel.onPasswordChange("12345678")
        viewModel.onConfirmPasswordChange("12345678")

        //when
        viewModel.onCreateAccountClick()

        //then
        assertThat(viewModel.uiState.value.nameError).isNotNull()
        assertThat(viewModel.uiState.value.submitRequested).isFalse()
    }

    @Test
    fun onCreateAccountClick_whenNameExceeds50Chars_setsNameError() {
        //given
        viewModel.onNameChange("a".repeat(51))
        viewModel.onEmailChange("brenno@gmail.com")
        viewModel.onPasswordChange("12345678")
        viewModel.onConfirmPasswordChange("12345678")

        //when
        viewModel.onCreateAccountClick()

        //then
        assertThat(viewModel.uiState.value.nameError).isNotNull()
    }

    @Test
    fun onCreateAccountClick_whenEmailHasNoAtSign_setsEmailError() {
        //given
        viewModel.onNameChange("Brenno")
        viewModel.onEmailChange("brenno.gmail.com")
        viewModel.onPasswordChange("12345678")
        viewModel.onConfirmPasswordChange("12345678")

        //when
        viewModel.onCreateAccountClick()

        //then
        assertThat(viewModel.uiState.value.emailError).isNotNull()
    }

    @Test
    fun onCreateAccountClick_whenEmailHasNoDot_setsEmailError() {
        //given
        viewModel.onNameChange("Brenno")
        viewModel.onEmailChange("brenno@gmailcom")
        viewModel.onPasswordChange("12345678")
        viewModel.onConfirmPasswordChange("12345678")

        //when
        viewModel.onCreateAccountClick()

        //then
        assertThat(viewModel.uiState.value.emailError).isNotNull()
    }

    @Test
    fun onCreateAccountClick_whenPasswordTooShort_setsPasswordError() {
        //given
        viewModel.onNameChange("Brenno")
        viewModel.onEmailChange("brenno@gmail.com")
        viewModel.onPasswordChange("1234567")
        viewModel.onConfirmPasswordChange("1234567")

        //when
        viewModel.onCreateAccountClick()

        //then
        assertThat(viewModel.uiState.value.passwordError).isNotNull()
    }

    @Test
    fun onCreateAccountClick_whenPasswordsDoNotMatch_setsConfirmPasswordError() {
        //given
        viewModel.onNameChange("Brenno")
        viewModel.onEmailChange("brenno@gmail.com")
        viewModel.onPasswordChange("12345678")
        viewModel.onConfirmPasswordChange("87654321")

        //when
        viewModel.onCreateAccountClick()

        //then
        assertThat(viewModel.uiState.value.confirmPasswordError).isNotNull()
    }

    @Test
    fun onConfirmPasswordFocusLost_whenConfirmIsEmpty_doesNotSetError() {
        //given
        viewModel.onPasswordChange("12345678")

        //when
        viewModel.onConfirmPasswordFocusLost()

        //then
        assertThat(viewModel.uiState.value.confirmPasswordError).isNull()
    }

    @Test
    fun onConfirmPasswordFocusLost_whenPasswordsDoNotMatch_setsError() {
        //given
        viewModel.onPasswordChange("12345678")
        viewModel.onConfirmPasswordChange("999")

        //when
        viewModel.onConfirmPasswordFocusLost()

        //then
        assertThat(viewModel.uiState.value.confirmPasswordError).isNotNull()
    }

    @Test
    fun onConfirmPasswordFocusLost_whenPasswordsMatch_doesNotSetError() {
        //given
        viewModel.onPasswordChange("12345678")
        viewModel.onConfirmPasswordChange("12345678")

        //when
        viewModel.onConfirmPasswordFocusLost()

        //then
        assertThat(viewModel.uiState.value.confirmPasswordError).isNull()
    }
}
