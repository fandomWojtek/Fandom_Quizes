package com.fandom.fandom.quiz.quiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.communication.CommunicationManager
import com.fandom.fandom.quiz.game.Game
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class AwaitQuizInvitationViewModel(private val communicationManager: CommunicationManager, private val currentQuizManager: CurrentQuizManager) : ViewModel() {

    private val _invitationIncoming: MutableSharedFlow<Game> = MutableSharedFlow(replay = 0)
    val invitationIncoming: SharedFlow<Game> = _invitationIncoming

    private val _goToQuizScreen : MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0 )
    val goToQuizScreen : SharedFlow<Unit> =  _goToQuizScreen

    init {
        viewModelScope.launch(Dispatchers.IO) {
            communicationManager.gameInvitationReceived.collect {
                _invitationIncoming.emit(it)
            }
        }
    }

    fun acceptInvitation(game: Game) {
        viewModelScope.launch {
            currentQuizManager.acceptInvitationAndSendInfoThatYouAreReady(game)
        }
    }
}