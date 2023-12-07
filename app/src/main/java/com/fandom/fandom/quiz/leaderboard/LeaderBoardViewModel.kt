package com.fandom.fandom.quiz.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import com.fandom.fandom.quiz.remoteDb.UserEntity
import com.fandom.fandom.quiz.remoteDb.UsersDb
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class Top3User(val user1: UserEntity?, val user2: UserEntity?, val user3: UserEntity?)
class LeaderBoardViewModel(private val usersDb: UsersDb, private val currentQuizManager: CurrentQuizManager) : ViewModel() {

    private val _allUsers: MutableStateFlow<List<UserEntity>> = MutableStateFlow(emptyList())
    val allUsers: StateFlow<List<UserEntity>> = _allUsers

    val top3Users: Flow<Top3User> = _allUsers.map { users ->
        Top3User(users.getOrNull(0), users.getOrNull(1), users.getOrNull(2))
    }


    fun getAllPlayers() {
        viewModelScope.launch {
            currentQuizManager.clearCurrentQuiz()
            _allUsers.emit(usersDb.getAllUsers().sortedByDescending { it.points })
        }
    }

}