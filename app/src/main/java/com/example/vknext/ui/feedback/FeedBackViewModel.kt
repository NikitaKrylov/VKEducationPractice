package com.example.vknext.ui.feedback

import androidx.lifecycle.ViewModel
import com.example.vknext.core.common.secure.AuthContextProvider
import com.example.vknext.core.data.repositories.users.UserRepository
import com.example.vknext.ui.feedback.state.FeedBackState
import com.example.vknext.ui.login.state.toItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class FeedBackViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authContextProvider: AuthContextProvider,
) : ViewModel() {

    private val currentUserId by lazy {
        authContextProvider.getAuthContext()?.userId ?: -1
    }

    private val mutableState = MutableStateFlow<FeedBackState>(FeedBackState.Loading)
    val state = mutableState.asStateFlow()

    init {
        mutableState.update {
            FeedBackState.ShowFeedBack(
                searchItems = userRepository.getAll()
                    .filterNot { it.id == currentUserId }
                    .map { it.toItem() }
            )
        }
    }

    fun changeTextInput(value: String) {
        mutableState.update { currentState ->
            (currentState as? FeedBackState.ShowFeedBack)?.copy(
                search = value
            ) ?: currentState
        }
    }
}