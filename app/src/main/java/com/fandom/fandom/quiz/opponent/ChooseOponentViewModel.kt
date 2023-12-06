package com.fandom.fandom.quiz.opponent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.remoteDb.UserEntity
import com.fandom.fandom.quiz.remoteDb.UsersDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChooseOponentViewModel(private val usersDb: UsersDb) : ViewModel() {

    private val _activeUsers : MutableStateFlow<List<UserEntity>> = MutableStateFlow(emptyList())
    val activeUsers : StateFlow<List<UserEntity>> = _activeUsers

    fun getOpponents() {
        viewModelScope.launch {
            _activeUsers.emit(usersDb.getUserActiveInLastNMinutes())
        }
    }

}