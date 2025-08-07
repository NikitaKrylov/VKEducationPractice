package com.example.vknext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vknext.ui.AuthPath
import com.example.vknext.ui.CreateFeedBackRoute
import com.example.vknext.ui.FeedBackRoute
import com.example.vknext.ui.ProfileRoute
import com.example.vknext.ui.createFeedBack.CreateFeedBackScreen
import com.example.vknext.ui.feedback.FeedBackScreen
import com.example.vknext.ui.login.LoginScreen
import com.example.vknext.ui.profile.ProfileScreen
import com.example.vknext.ui.theme.VknextTheme
import com.example.vknext.ui.uikit.appbar.AppBar
import com.example.vknext.ui.uikit.snackbar.AppSnackbarState
import com.example.vknext.ui.uikit.snackbar.LocalSnackbarState
import com.example.vknext.ui.uikit.snackbar.SnackbarHost
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { AppSnackbarState(scope) }

            VknextTheme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false,
                LocalSnackbarState provides snackbarHostState,
            ) {
                val navController = rememberNavController()

                Surface(
                    color = Color(0xFFD7D8D9)
                ) {
                    Scaffold(
                        bottomBar = {
                            AppBar(
                                navController = navController
                            )
                        }
                    ) { paddings ->
                        NavHost(
                            navController = navController,
                            startDestination = AuthPath.baseRoute,
                            modifier = Modifier
                                .padding(paddings)
                        ) {
                            composable(route = AuthPath.baseRoute) {
                                LoginScreen(navController)
                            }


                            composable(route = ProfileRoute.baseRoute) {
                                ProfileScreen(
                                    navController = navController
                                )
                            }

                            composable(route = CreateFeedBackRoute.baseRoute) {
                                CreateFeedBackScreen(
                                    navController = navController
                                )
                            }

                            composable(route = FeedBackRoute.baseRoute) {
                                FeedBackScreen(
                                    navController = navController
                                )
                            }
                        }

                        SnackbarHost(
                            modifier = Modifier
                                .safeDrawingPadding()
                        )
                    }
                }

            }
        }
    }
}
