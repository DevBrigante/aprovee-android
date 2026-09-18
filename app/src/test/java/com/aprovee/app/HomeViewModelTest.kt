package com.aprovee.app

import com.aprovee.app.data.repository.FakeOnboardingRepositoryImpl
import com.aprovee.app.ui.screens.home.HomeViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun init_whenOnboardingIsNotCompleted_setsShowOnboarding() = runTest {
        // given
        val repository = FakeOnboardingRepositoryImpl(initialCompleted = false)

        //when
        val viewModel = HomeViewModel(repository)

        //then
        assertThat(viewModel.uiState.value.showOnboarding).isTrue()
    }

    @Test
    fun init_whenOnboardingIsCompleted_doesNotShowOnboarding() = runTest {
        // given
        val repository = FakeOnboardingRepositoryImpl(initialCompleted = true)

        // when
        val viewModel = HomeViewModel(repository)

        // then
        assertThat(viewModel.uiState.value.showOnboarding).isFalse()
    }

    @Test
    fun onOnboardingCompleted_marksOnboardingAsCompletedAndHidesIt() = runTest {
        // given
        val repository = FakeOnboardingRepositoryImpl(initialCompleted = false)
        val viewModel = HomeViewModel(repository)

        // when
        viewModel.onOnboardingCompleted()

        // then
        assertThat(viewModel.uiState.value.showOnboarding).isFalse()
    }
}