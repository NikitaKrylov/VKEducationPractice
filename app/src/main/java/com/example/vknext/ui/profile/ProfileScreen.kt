package com.example.vknext.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.vknext.ui.AuthPath
import com.example.vknext.ui.profile.components.Profile
import com.example.vknext.ui.profile.state.ProfileState

@Composable
fun ProfileScreen(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    val actions = object : ProfileActionsListener {
        override fun onLogOut() {
            viewModel.logOut()
            navController.navigate(AuthPath.toRoute())
        }

    }

    when (state) {
        ProfileState.Error -> {

        }
        ProfileState.Loading -> {

        }
        is ProfileState.ShowProfile -> {
            Profile(
                state = state,
                actions = actions,
            )
        }
    }
}