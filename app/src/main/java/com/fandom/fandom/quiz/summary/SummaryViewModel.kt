package com.fandom.fandom.quiz.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.auth.domain.CurrentUserRepository
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import com.fandom.fandom.quiz.remoteDb.UserEntity
import com.fandom.fandom.quiz.remoteDb.UsersDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SummaryViewModel(
    private val quizManager: CurrentQuizManager,
    val usersDb: UsersDb,
    val userRepository: CurrentUserRepository,
    val currentQuizManager: CurrentQuizManager,

    ) : ViewModel() {

    private val _opponentData: MutableStateFlow<UserEntity?> = MutableStateFlow(null)
    val opponentData: StateFlow<UserEntity?> = _opponentData

    private val _currentUserData: MutableStateFlow<UserEntity?> = MutableStateFlow(null)
    val currentUserData: StateFlow<UserEntity?> = _currentUserData

    private val _currentUserPoints: MutableStateFlow<Int> = MutableStateFlow(0)
    val currentUserPoints: StateFlow<Int> = _currentUserPoints

    private val _opponentPoints: MutableStateFlow<Int> = MutableStateFlow(0)
    val opponentPoints: StateFlow<Int> = _opponentPoints

    private val _didYouWon: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 0)
    val didYouWon: SharedFlow<Boolean> = _didYouWon


    fun getUsersData() {
        viewModelScope.launch {
            val opponent = usersDb.getUserById(quizManager.currentOpponent.value)
            _opponentData.emit(opponent)
            _currentUserData.emit(userRepository.getCurrentUser())
        }
    }

    fun summarizeTheQuiz() {
        viewModelScope.launch {
            currentQuizManager.currentUserResponses.filterNotNull().combine(currentQuizManager.currentOpponentResponses.filterNotNull()) { userResponses, opponentResponses ->
                var sum = 0
                var opponentSum = 0
                userResponses.list.indices.forEach { index ->
                    if (userResponses.list[index].correct && !opponentResponses.list[index].correct) {
                        sum += 2
                    } else if (!userResponses.list[index].correct && opponentResponses.list[index].correct) {
                        opponentSum += 2
                    } else if (userResponses.list[index].correct && opponentResponses.list[index].correct) {
                        if (userResponses.list[index].time > opponentResponses.list[index].time) {
                            sum += 1
                        } else if (userResponses.list[index].time < opponentResponses.list[index].time) {
                            opponentSum += 1
                        }
                    }
                }
                launch {
                    delay(1000)
                    _didYouWon.emit(sum > opponentSum)
                }

                _currentUserPoints.emit(sum)
                _opponentPoints.emit(opponentSum)
                if (currentQuizManager.isCurrentHost.value) {
                    usersDb.getUserById(userRepository.getCurrentUser()!!.id)?.let {
                        usersDb.updateUser(it.copy(points = it.points + sum))
                    }
                    usersDb.getUserById(quizManager.currentOpponent.value)?.let {
                        usersDb.updateUser(it.copy(points = it.points + opponentSum))
                    }
                }

            }.collect()
        }
    }
}