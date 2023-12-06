package com.fandom.fandom.quiz.opponent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.auth.domain.UserRepository
import com.fandom.fandom.quiz.remoteDb.UserEntity
import com.fandom.fandom.quiz.remoteDb.UsersDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChooseOponentViewModel(private val usersDb: UsersDb, private val userRepository: UserRepository) : ViewModel() {

    private val _activeUsers : MutableStateFlow<List<UserEntity>> = MutableStateFlow(emptyList())
    val activeUsers : StateFlow<List<UserEntity>> = _activeUsers

    fun getOpponents() {
        viewModelScope.launch {
            val currentUserId = userRepository.getCurrentUser()?.id
            val activeUsers = usersDb.getUserActiveInLastNMinutes().filter { it.id != currentUserId }
            _activeUsers.emit(activeUsers)
        }
    }

}