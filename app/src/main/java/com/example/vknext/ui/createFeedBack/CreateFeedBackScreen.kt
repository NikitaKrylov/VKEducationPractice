package com.example.vknext.ui.createFeedBack

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.vknext.ui.createFeedBack.components.CreateFeedBack
import com.example.vknext.ui.createFeedBack.state.CreateFeedbackSideEffect
import kotlinx.coroutines.flow.filterNotNull


@Composable
fun CreateFeedBackScreen(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<CreateFeedBackViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val sideEffect = viewModel.sideEffect.collectAsStateWithLifecycle(null)

    val actions = object : CreateFeedbackListener {
        override fun updatePointItem(pointId: Long, isSelected: Boolean) {
            viewModel.updatePointItem(pointId, isSelected)
        }

        override fun saveForm() {
            viewModel.save()
        }

    }

    CreateFeedBack(
        state = state,
        actions = actions,
    )

    LaunchedEffect(sideEffect) {
        snapshotFlow { sideEffect.value }.filterNotNull().collect { sideEffect ->
            when (sideEffect) {
                CreateFeedbackSideEffect.Created -> {
                    navController.popBackStack()
                }
                CreateFeedbackSideEffect.Error -> TODO()
            }
        }
    }

}