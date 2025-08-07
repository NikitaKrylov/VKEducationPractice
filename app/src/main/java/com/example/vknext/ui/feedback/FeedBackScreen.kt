package com.example.vknext.ui.feedback

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.vknext.ui.CreateFeedBackRoute
import com.example.vknext.ui.NavArgs
import com.example.vknext.ui.feedback.components.FeedBack
import com.example.vknext.ui.feedback.state.FeedBackState


@Composable
fun FeedBackScreen(
    navController: NavHostController,
){
    val viewModel = hiltViewModel<FeedBackViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()


    val actions = object : FeedBackListener {
        override fun onItemClick(accountId: Long) {
            navController.navigate(CreateFeedBackRoute.toRoute(
                NavArgs.USER_ID to accountId
            ))
        }

        override fun onSearchTextChanged(value: String) {
            viewModel.changeTextInput(value)
        }

    }

    when (state) {
        FeedBackState.Error -> {

        }
        FeedBackState.Loading -> {

        }
        is FeedBackState.ShowFeedBack -> {
            FeedBack(
                state = state as FeedBackState.ShowFeedBack,
                actions = actions,
            )
        }
    }
}