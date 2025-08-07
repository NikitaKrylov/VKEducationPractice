package com.example.vknext.ui.knowledge

import androidx.lifecycle.ViewModel
import com.example.vknext.core.data.repositories.knowladge.KnowladgeRepository
import com.example.vknext.core.data.repositories.knowladge.KnowladgeRepositoryImpl
import com.example.vknext.ui.knowledge.state.KnowladgeState
import com.example.vknext.ui.knowledge.state.toItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class KnowLedgeViewModel @Inject constructor() : ViewModel() {
    private val knowladgeRepository: KnowladgeRepository = KnowladgeRepositoryImpl()

    private val mutableState = MutableStateFlow(KnowladgeState())
    val state = mutableState.asStateFlow()

    init {
        fetchKnowladgeBase()
    }

    fun fetchKnowladgeBase() {
        mutableState.update { currentState ->
            currentState.copy(
                items = knowladgeRepository.getAll().map {
                    it.toItem()
                }
            )
        }
    }
}