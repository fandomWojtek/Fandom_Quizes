package com.fandom.fandom.quiz.communication

import com.fandom.fandom.quiz.game.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class CommunicationManager(private val scope: CoroutineScope) {

    private val _gameInvitationReceived : MutableSharedFlow<Game> = MutableSharedFlow(replay = 0 )
    val gameInvitationReceived : SharedFlow<Game> =  _gameInvitationReceived

    fun receivedGameInvitation(game: Game) {
        scope.launch {
            _gameInvitationReceived.emit(game)
        }
    }

    private val _acceptInvitation : MutableSharedFlow<InvitationAccepted> = MutableSharedFlow(replay = 0 )
    val acceptInvitation : SharedFlow<InvitationAccepted> =  _acceptInvitation

    fun acceptInvitation(accepted:InvitationAccepted) {
        scope.launch {
            _acceptInvitation.emit(accepted)
        }
    }

}