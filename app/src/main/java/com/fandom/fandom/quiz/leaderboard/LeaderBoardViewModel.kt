package com.fandom.fandom.quiz.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.remoteDb.UserEntity
import com.fandom.fandom.quiz.remoteDb.UsersDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LeaderBoardViewModel(private val usersDb: UsersDb) : ViewModel() {

    private val _allUsers : MutableStateFlow<List<UserEntity>> = MutableStateFlow(emptyList())
    val allUsers : StateFlow<List<UserEntity>> = _allUsers

    fun getAllPlayers() {
        viewModelScope.launch {
            _allUsers.emit(usersDb.getAllUsers())
        }
    }

}