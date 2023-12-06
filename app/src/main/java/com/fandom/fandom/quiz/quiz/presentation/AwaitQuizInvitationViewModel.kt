package com.fandom.fandom.quiz.quiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.communication.CommunicationManager
import com.fandom.fandom.quiz.notification.send.Game
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import com.fandom.fandom.quiz.remoteDb.UserEntity
import com.fandom.fandom.quiz.remoteDb.UsersDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

data class QuizInvitation(val game: Game, val fromUser: UserEntity)
class AwaitQuizInvitationViewModel(
    private val communicationManager: CommunicationManager,
    private val currentQuizManager: CurrentQuizManager,
    private val usersDb: UsersDb
    ) : ViewModel() {

    private val _invitationIncoming: MutableSharedFlow<QuizInvitation> = MutableSharedFlow(replay = 0)
    val invitationIncoming: SharedFlow<QuizInvitation> = _invitationIncoming

    private val _goToQuizScreen: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0)
    val goToQuizScreen: SharedFlow<Unit> = _goToQuizScreen

    init {
        viewModelScope.launch(Dispatchers.IO) {
            communicationManager.gameInvitationReceived.collect {
                usersDb.getUserById(it.fromUser)?.let { user ->
                    _invitationIncoming.emit(QuizInvitation(it, user))
                }

            }
        }
    }

    fun respondToInvitation(game: Game,accepted:Boolean) {
        viewModelScope.launch {
            if(accepted){
                currentQuizManager.acceptInvitationAndSendInfoThatYouAreReady(game)
                _goToQuizScreen.emit(Unit)
            }else{
                currentQuizManager.rejectInvitation(game)
            }
        }
    }
}