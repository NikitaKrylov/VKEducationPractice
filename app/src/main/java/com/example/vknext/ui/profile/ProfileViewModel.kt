package com.example.vknext.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknext.core.common.secure.AuthContextProvider
import com.example.vknext.core.data.repositories.answers.AnswersRepository
import com.example.vknext.domain.GetFeedBackWithSender
import com.example.vknext.ui.profile.state.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authContextProvider: AuthContextProvider,
    private val answersRepository: AnswersRepository,
    private val getFeedBackWithSender: GetFeedBackWithSender
) : ViewModel() {

    private val mutableState = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val state = mutableState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {

            val context = authContextProvider.getAuthContext()
            val answers = getFeedBackWithSender(
                accountId = context?.userId ?: -1
            )

            mutableState.update {
                context?.userData?.let {
                    ProfileState.ShowProfile(
                        avatarId = it.avatarId,
                        thanks = answers,
                        username = listOf(
                            it.firstName,
                            it.lastName,
                        ).joinToString(separator = " "),
                        role = it.role ?: "",
                        totalThanks = answers.size,
                    )
                } ?: ProfileState.Error
            }
        }
    }

    fun logOut() {
        authContextProvider.clear()
    }

}