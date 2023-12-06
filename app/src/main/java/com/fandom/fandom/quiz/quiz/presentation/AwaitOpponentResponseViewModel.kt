package com.fandom.fandom.quiz.quiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.communication.CommunicationManager
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random

data class QuestionResponse(val number: Int, val time: Int, val correct: Boolean)
data class OpponentResponses(val list: List<QuestionResponse>)
class AwaitOpponentResponseViewModel(
    private val communicationManager: CommunicationManager,
    private val currentQuizManager: CurrentQuizManager
) : ViewModel() {

    private val _opponentResponseState: MutableStateFlow<OpponentResponses> = MutableStateFlow(OpponentResponses(emptyList()))
    val opponentResponseState: StateFlow<OpponentResponses> = _opponentResponseState


    init {
        viewModelScope.launch(Dispatchers.Default) {
            communicationManager.questionResponse.combine(currentQuizManager.isCurrentHost) { responses, isHost ->
                val quizResponses = OpponentResponses(responses.answers.indices.map {
                    QuestionResponse(it, responses.time[it], responses.answers[it] == 1)
                })
                _opponentResponseState.emit(quizResponses)
                if (isHost && responses.answers.size == currentQuizManager.currentQuizState.value?.questions?.size) {
                    currentQuizManager.persistCurrentOpponentResponses(quizResponses)
                }
            }.collect()
        }

    }
}