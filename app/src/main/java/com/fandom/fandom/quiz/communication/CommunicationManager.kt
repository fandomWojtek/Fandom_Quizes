package com.fandom.fandom.quiz.communication

import com.fandom.fandom.quiz.notification.send.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class CommunicationManager(private val scope: CoroutineScope) {

    private val _gameInvitationReceived: MutableSharedFlow<Game> = MutableSharedFlow(replay = 0)
    val gameInvitationReceived: SharedFlow<Game> = _gameInvitationReceived

    fun receivedGameInvitation(game: Game) {
        scope.launch {
            _gameInvitationReceived.emit(game)
        }
    }

    private val _acceptInvitation: MutableSharedFlow<InvitationAccepted> = MutableSharedFlow(replay = 0)
    val acceptInvitation: SharedFlow<InvitationAccepted> = _acceptInvitation

    fun acceptInvitation(accepted: InvitationAccepted) {
        scope.launch {
            _acceptInvitation.emit(accepted)
        }
    }

    private val _questionResponse: MutableSharedFlow<SendQuestionResponse> = MutableSharedFlow(replay = 0)
    val questionResponse: SharedFlow<SendQuestionResponse> = _questionResponse

    fun questionResponse(questionResponse: SendQuestionResponse) {
        scope.launch {
            _questionResponse.emit(questionResponse)
        }
    }

}