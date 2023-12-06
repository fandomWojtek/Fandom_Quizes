package com.fandom.fandom.quiz.quiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.quiz.api.Quiz
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class QuizViewModel(private val currentQuizManager: CurrentQuizManager) : ViewModel() {

    private val _quiz: MutableStateFlow<Quiz?> = MutableStateFlow(null)
    val quiz: StateFlow<Quiz?> = _quiz

    private val _startTime: MutableStateFlow<Long> = MutableStateFlow(0)
    val startTime: StateFlow<Long> = _startTime
    private val _endTime: MutableStateFlow<Long> = MutableStateFlow(0)
    val endTime: StateFlow<Long> = _endTime

    private val _responses: MutableStateFlow<List<QuestionResponse>> = MutableStateFlow(emptyList())
    val responses: StateFlow<List<QuestionResponse>> = _responses

    private val _goToNextQuestion: MutableStateFlow<Int> = MutableStateFlow(0)
    val goToNextQuestion: MutableStateFlow<Int> = _goToNextQuestion

    private val _finishFlowOfQuiz: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0)
    val finishFlowOfQuiz: SharedFlow<Unit> = _finishFlowOfQuiz


    fun getQuiz() {
        viewModelScope.launch {
            _quiz.emit(currentQuizManager.currentQuizState.value)
        }
    }

    fun startTimer() {
        viewModelScope.launch {
            _startTime.emit(System.currentTimeMillis())
        }
    }

    fun endTimer() {
        viewModelScope.launch { _endTime.emit(System.currentTimeMillis()) }
    }

    fun handleAnswer(isCorrect: Boolean, questionNumber: Int) {
        endTimer()
        val time = (endTime.value - startTime.value).toInt()
        val response = QuestionResponse(questionNumber, time, isCorrect)
        val currentResponses = responses.value
        val newResponses = currentResponses + response
        viewModelScope.launch {
            delay(800)
            currentQuizManager.sendQuestionResponse(OpponentResponses(newResponses))
            if (_goToNextQuestion.value + 1 == quiz.value?.questions?.size) {
                _finishFlowOfQuiz.emit(Unit)
            } else {
                _goToNextQuestion.emit(_goToNextQuestion.value + 1)
                _responses.emit(newResponses)
            }
        }
    }
}
