package com.fandom.fandom.quiz.quiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.quiz.api.Quiz
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModel(private val currentQuizManager: CurrentQuizManager) : ViewModel() {

    private val _quiz: MutableStateFlow<Quiz?> = MutableStateFlow(null)
    val quiz: StateFlow<Quiz?> = _quiz

    fun getQuiz() {
        viewModelScope.launch {
            _quiz.emit(currentQuizManager.currentQuizState.value)
        }
    }
}
