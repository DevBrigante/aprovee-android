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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.aprovee.app.ui.screens.auth.LoadingScreen
import com.aprovee.app.ui.screens.auth.WelcomeScreen
import com.aprovee.app.ui.screens.createaccount.CreateAccountScreen
import com.aprovee.app.ui.screens.login.LoginScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthFlowRoute,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX =  { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
            ) + fadeIn(animationSpec = tween(100, delayMillis = 120))
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth / 4 },
                animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing)
            ) + fadeOut(animationSpec = tween(durationMillis = 80))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth / 4 },
                animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
            ) + fadeIn(animationSpec = tween(100, delayMillis = 120))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing)
            ) + fadeOut(animationSpec = tween(80))
        }
    ) {
        navigation<AuthFlowRoute>(startDestination = LoginRoute) {
            composable<LoginRoute> {
                LoginScreen(
                    onNavigateToHome = {
                        navController.navigate(HomeRoute) {
                            popUpTo(AuthFlowRoute) { inclusive = true }
                        }
                    },
                    onNavigateToCreateAccount = {
                        navController.navigate(CreateAccountRoute)
                    }
                )
            }
            composable<CreateAccountRoute> {
                CreateAccountScreen(
                    onNavigateToHome = {
                        navController.navigate(LoadingRoute) {
                            popUpTo(AuthFlowRoute) { inclusive = true }
                        }
                    },
                    onNavigateBack = { navController.popBackStack()}
                )
            }
            composable<LoadingRoute> {
                LoadingScreen()
            }
            composable<WelcomeRoute> {
                WelcomeScreen(onContinue = {
                    navController.navigate(LoginRoute) {
                        popUpTo(AuthFlowRoute) { inclusive = true }
                    }
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
    title: String,
    onNavigate: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge
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