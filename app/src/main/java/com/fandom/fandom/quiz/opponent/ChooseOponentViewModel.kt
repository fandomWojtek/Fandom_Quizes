package com.fandom.fandom.quiz.opponent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import com.fandom.fandom.quiz.remoteDb.UserEntity
import com.fandom.fandom.quiz.remoteDb.UsersDb
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChooseOponentViewModel(private val usersDb: UsersDb, private val currentQuizManager: CurrentQuizManager, val categoryId: String) : ViewModel() {

    private val _activeUsers: MutableStateFlow<List<UserEntity>> = MutableStateFlow(emptyList())
    val activeUsers: StateFlow<List<UserEntity>> = _activeUsers

    private val _waitForUserResponded : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val waitForUserRespond : StateFlow<Boolean> =  _waitForUserResponded

    private val _goToQuiz : MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0 )
    val goToQuiz : SharedFlow<Unit> =  _goToQuiz

    fun getOpponents() {
        viewModelScope.launch {
            _activeUsers.emit(usersDb.getUserActiveInLastNMinutes())
        }
    }

    fun inviteUser(user: UserEntity) {
        viewModelScope.launch {
            _waitForUserResponded.emit(true)
            currentQuizManager.loadQuizAndInviteUserToIt(user, categoryId)
            _waitForUserResponded.emit(false)
            _goToQuiz.emit(Unit)
        }
    }

}