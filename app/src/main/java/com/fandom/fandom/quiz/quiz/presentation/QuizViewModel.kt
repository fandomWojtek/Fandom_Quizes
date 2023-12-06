package com.fandom.fandom.quiz.quiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.quiz.api.Quiz
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
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

    private val _goToNextQuestion: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val goToNextQuestion: MutableStateFlow<Boolean> = _goToNextQuestion


    fun getQuiz() {
        viewModelScope.launch {
            _quiz.emit(currentQuizManager.currentQuizState.value)
        }
    }

    fun startTimer() {
        viewModelScope.launch {
            _goToNextQuestion.emit(false)
            _startTime.emit(System.currentTimeMillis())
        }
    }

    fun endTimer() {
        viewModelScope.launch { _endTime.emit(System.currentTimeMillis()) }
    }

    fun handleAnswer(isCorrect: Boolean, questionNumber: Int) {
        endTimer()
        val time = (endTime.value - startTime.value).toInt()
        val response: QuestionResponse = QuestionResponse(questionNumber, time, isCorrect)
        val currentResponses = responses.value
        viewModelScope.launch {
            _responses.emit(currentResponses + response)
            _goToNextQuestion.emit(true)
        }
    }
}
