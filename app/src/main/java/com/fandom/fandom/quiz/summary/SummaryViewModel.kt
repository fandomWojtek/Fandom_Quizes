package com.fandom.fandom.quiz.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.auth.domain.CurrentUserRepository
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import com.fandom.fandom.quiz.quiz.presentation.OpponentResponses
import com.fandom.fandom.quiz.quiz.presentation.QuestionResponse
import com.fandom.fandom.quiz.remoteDb.UserEntity
import com.fandom.fandom.quiz.remoteDb.UsersDb
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class SummaryViewModel(
    private val quizManager: CurrentQuizManager,
    val usersDb: UsersDb,
    val userRepository: CurrentUserRepository,
    val currentQuizManager: CurrentQuizManager
) : ViewModel() {

    private val _opponentData: MutableStateFlow<UserEntity?> = MutableStateFlow(null)
    val opponentData: StateFlow<UserEntity?> = _opponentData

    private val _currentUserData: MutableStateFlow<UserEntity?> = MutableStateFlow(null)
    val currentUserData: StateFlow<UserEntity?> = _currentUserData

    private val _currentUserPoints: MutableStateFlow<Int> = MutableStateFlow(0)
    val currentUserPoints: StateFlow<Int> = _currentUserPoints

    private val _opponentPoints: MutableStateFlow<Int> = MutableStateFlow(0)
    val opponentPoints: StateFlow<Int> = _opponentPoints


    fun getUsersData() {
        viewModelScope.launch {
            val opponent = usersDb.getUserById(quizManager.currentOpponent.value)
            _opponentData.emit(opponent)
            _currentUserData.emit(userRepository.getCurrentUser())
        }
    }

    fun summarizeTheQuiz() {
        viewModelScope.launch {
            currentQuizManager.currentUserResponses.combine(currentQuizManager.currentOpponentResponses) { userResponses, opponentResponses ->
                if (userResponses.list[0].correct && !opponentResponses.list[0].correct) {
                    _currentUserPoints.emit(currentUserPoints.value + 2)
                } else if (!userResponses.list[0].correct && opponentResponses.list[0].correct) {
                    _opponentPoints.emit(opponentPoints.value + 2)
                }
            }.collect()
        }
    }
}