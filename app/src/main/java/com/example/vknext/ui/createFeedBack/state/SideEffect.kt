package com.example.vknext.ui.createFeedBack.state

sealed class CreateFeedbackSideEffect {
    data object Created : CreateFeedbackSideEffect()

    data object Error : CreateFeedbackSideEffect()
}