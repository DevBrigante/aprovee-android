package com.aprovee.app.ui.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.aprovee.app.domain.model.toErrorType
import com.aprovee.app.ui.screens.auth.ErrorScreen
import com.aprovee.app.ui.screens.auth.LoadingScreen
import com.aprovee.app.ui.screens.auth.SignupFlowViewModel
import com.aprovee.app.ui.screens.auth.SplashScreen
import com.aprovee.app.ui.screens.auth.WelcomeScreen
import com.aprovee.app.ui.screens.createaccount.CreateAccountScreen
import com.aprovee.app.ui.screens.login.LoginScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashRoute, enterTransition = {
        slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(100, delayMillis = 120))
    }, exitTransition = {
        slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth / 4 },
            animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing)
        ) + fadeOut(animationSpec = tween(durationMillis = 80))
    }, popEnterTransition = {
        slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth / 4 },
            animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(100, delayMillis = 120))
    }, popExitTransition = {
        slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing)
        ) + fadeOut(animationSpec = tween(80))
    }) {

        composable<SplashRoute>(
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 400 )) }
        ) {
            SplashScreen(onTimeout = {
                navController.navigate(AuthFlowRoute) {
                    popUpTo(SplashRoute) { inclusive = true }
                }
            })
        }

        navigation<AuthFlowRoute>(startDestination = LoginRoute) {
            composable<LoginRoute>(
                enterTransition = { fadeIn(animationSpec = tween(durationMillis = 400)) }
            ) {
                LoginScreen(onNavigateToHome = {
                    navController.navigate(HomeRoute) {
                        popUpTo(AuthFlowRoute) { inclusive = true }
                    }
                }, onNavigateToCreateAccount = {
                    navController.navigate(CreateAccountRoute)
                })
            }
            composable<CreateAccountRoute> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(AuthFlowRoute)
                }
                val signupFlowViewModel: SignupFlowViewModel = viewModel(
                    viewModelStoreOwner = parentEntry
                )
                CreateAccountScreen(
                    signupFlowViewModel = signupFlowViewModel,
                    onNavigateToLoading = {
                        navController.navigate(LoadingRoute)
                    },
                    onNavigateToError = { errorType ->
                        navController.navigate(ErrorRoute(errorType))
                    },
                    onNavigateBack = { navController.popBackStack() })
            }
            composable<LoadingRoute> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(AuthFlowRoute)
                }
                val signupFlowViewModel: SignupFlowViewModel = viewModel(
                    viewModelStoreOwner = parentEntry
                )
                LoadingScreen(signupFlowViewModel = signupFlowViewModel, onNavigateToWelcome = {
                    navController.navigate(WelcomeRoute)
                }, onNavigateToError = { errorType ->
                    navController.navigate(ErrorRoute(errorType))
                })
            }
            composable<ErrorRoute> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(AuthFlowRoute)
                }
                val signupFlowViewModel: SignupFlowViewModel = viewModel(
                    viewModelStoreOwner = parentEntry
                )
                val errorRoute = backStackEntry.toRoute<ErrorRoute>()
                ErrorScreen(
                    errorType = errorRoute.errorType.toErrorType(),
                    signupFlowViewModel = signupFlowViewModel,
                    onNavigateToLoading = {
                        navController.navigate(LoadingRoute) {
                            popUpTo<ErrorRoute> { inclusive = true }
                        }
                    },
                    onGoBack = {
                        navController.popBackStack<CreateAccountRoute>(inclusive = false)
                    })
            }

            composable<WelcomeRoute> {
                WelcomeScreen(onContinue = {
                    navController.popBackStack<LoginRoute>(inclusive = false)
                })
            }
        }
        composable<HomeRoute> {
            PlaceholderScreen(title = "Home")
        }
    }
}

@Composable
private fun PlaceholderScreen(
    title: String, onNavigate: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title, style = MaterialTheme.typography.headlineLarge
                )
                if (onNavigate != null) {
                    Button(onClick = onNavigate) {
                        Text("Avançar")
                    }
                }
            }
        }
    }

}