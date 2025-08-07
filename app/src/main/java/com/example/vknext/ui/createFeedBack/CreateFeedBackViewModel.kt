package com.example.vknext.ui.createFeedBack

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknext.core.common.secure.AuthContextProvider
import com.example.vknext.core.data.models.FeedBackPoint
import com.example.vknext.core.data.models.FeedBackType
import com.example.vknext.core.data.repositories.answers.AnswersRepository
import com.example.vknext.core.data.repositories.forms.FormsFeedbackRepository
import com.example.vknext.ui.NavArgs
import com.example.vknext.ui.createFeedBack.state.CreateFeedBackState
import com.example.vknext.ui.createFeedBack.state.CreateFeedbackSideEffect
import com.example.vknext.ui.createFeedBack.state.PointItem
import com.example.vknext.ui.createFeedBack.state.toItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateFeedBackViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val formsFeedbackRepository: FormsFeedbackRepository,
    private val authContextProvider: AuthContextProvider,
    private val answersRepository: AnswersRepository,
) : ViewModel() {
    private val recipientId by lazy {
        savedStateHandle.get<String>(NavArgs.USER_ID)?.toLong() ?: -1
    }

    private val currentUserId by lazy {
        authContextProvider.getAuthContext()?.userId ?: -1
    }

    private val mutableState = MutableStateFlow(CreateFeedBackState())
    val state = mutableState.asStateFlow()

    private val mutableSideEffect = Channel<CreateFeedbackSideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()


    init {
        val form = formsFeedbackRepository.getByType(FeedBackType.THANKS)

        mutableState.update {
            CreateFeedBackState(
                groups = form!!.groups.map { it.toItem() }
            )
        }
    }

    fun updatePointItem(pointId: Long, isSelected: Boolean) {
        mutableState.update { currentState ->
            currentState.copy(
                groups = currentState.groups.map { group ->
                    group.copy(
                        points = group.points.map { point ->
                            point.copy(isSelected = isSelected).takeIf {
                                it.id == pointId
                            } ?: point
                        }
                    )
                }
            )
        }
    }

    fun save() {
        viewModelScope.launch {
            val state = mutableState.value

            answersRepository.createAnswer(
                senderId = currentUserId,
                recipientId = recipientId,
                feedbackType = FeedBackType.THANKS,
                selectedPoints = state.getSelectedPoints().map {
                    FeedBackPoint(
                        id = it.id,
                        name = it.text
                    )
                },
                groupHashtag = "",
            )
            mutableSideEffect.send(CreateFeedbackSideEffect.Created)
        }
    }


    private fun CreateFeedBackState.getSelectedPoints() : List<PointItem> {
        return this.groups.map { it.points }.flatten().filter { it.isSelected }
    }

}