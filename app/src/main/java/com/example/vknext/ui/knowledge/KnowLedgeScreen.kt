package com.example.vknext.ui.knowledge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.vknext.ui.knowledge.components.Knowladge
import com.example.vknext.ui.knowledge.state.KnowladgeListener


@Composable
fun KnowLedgeScreen(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<KnowLedgeViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val actions = object : KnowladgeListener {
        override fun onItemClick(id: Int) {
            // TODO
        }
    }

    Knowladge(
        state = state,
        actions = actions,
    )
}