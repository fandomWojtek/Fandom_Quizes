package com.fandom.fandom.quiz.quiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.communication.CommunicationManager
import com.fandom.fandom.quiz.quiz.api.Quiz
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import com.fandom.fandom.quiz.remoteDb.UsersDb
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random

data class QuestionResponse(val number: Int, val time: Int, val correct: Boolean)
data class OpponentResponses(val list: List<QuestionResponse>)

data class QuizMetaData(val quizId: Int, val questionNumber: Int)
class AwaitOpponentResponseViewModel(
    private val communicationManager: CommunicationManager,
    private val currentQuizManager: CurrentQuizManager,
    private val usersDb: UsersDb
) : ViewModel() {

    private val _opponentResponseState: MutableStateFlow<OpponentResponses> = MutableStateFlow(OpponentResponses(emptyList()))
    val opponentResponseState: StateFlow<OpponentResponses> = _opponentResponseState


    private val _quizMetaData: MutableSharedFlow<QuizMetaData> = MutableSharedFlow(replay = 1)
    val quizMetaData: SharedFlow<QuizMetaData> = _quizMetaData

    private val _currentOpponentName: MutableStateFlow<String> = MutableStateFlow("")
    val currentOpponentName: StateFlow<String> = _currentOpponentName

    init {
        viewModelScope.launch(Dispatchers.Default) {
            communicationManager.questionResponse.combine(currentQuizManager.isCurrentHost) { responses, isHost ->
                val quizResponses = OpponentResponses(responses.answers.indices.map {
                    QuestionResponse(it, responses.time[it], responses.answers[it] == 1)
                })
                _opponentResponseState.emit(quizResponses)
                if (responses.answers.size == currentQuizManager.currentQuizState.value?.questions?.size) {
                    currentQuizManager.persistCurrentOpponentResponses(quizResponses)
                }
            }.collect()
        }

        viewModelScope.launch {
            currentQuizManager.currentQuizState.filterNotNull().collect { quiz ->
                _quizMetaData.emit(QuizMetaData(quiz.id, quiz.questions.size))
            }
        }
        viewModelScope.launch {
            currentQuizManager.currentOpponent.filter { it.isNotEmpty() }.collect { opponent ->
                usersDb.getUserById(opponent)?.let { _currentOpponentName.emit(it.userName) }

            }
        }
    }

}