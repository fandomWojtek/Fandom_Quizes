package com.fandom.fandom.quiz.quiz.domain

import com.fandom.fandom.quiz.auth.domain.UserRepository
import com.fandom.fandom.quiz.categories.categoryList
import com.fandom.fandom.quiz.communication.CommunicationManager
import com.fandom.fandom.quiz.game.Game
import com.fandom.fandom.quiz.notification.send.SendPush
import com.fandom.fandom.quiz.quiz.api.Quiz
import com.fandom.fandom.quiz.remoteDb.UserEntity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*

class CurrentQuizManager(
    private val loadQuizUseCase: LoadQuizUseCase,
    private val sendPush: SendPush,
    private val userRepository: UserRepository,
    private val communicationManager: CommunicationManager
) {

    private val _currentQuizState: MutableStateFlow<Quiz?> = MutableStateFlow(null)
    val currentQuizState: StateFlow<Quiz?> = _currentQuizState

    suspend fun loadQuizAndInviteUserToIt(userToInvite: UserEntity, siteId: String):Boolean {
        val quiz = loadQuizUseCase.loadQuiz(siteId)

        val currentUser = userRepository.getCurrentUser()!!
        sendPush.sendInvitationToGame(userToInvite, Game(categoryList.find { it.id == siteId }!!.name, quiz, currentUser))
        var accepted:Boolean = false
        communicationManager.acceptInvitation.takeWhile {
            accepted = it.accepted
            !(it.forQuiz == quiz.id && it.fromUserEntity.id == currentUser.id)
        }.collect()
        return accepted
    }

    suspend fun acceptInvitationAndSendInfoThatYouAreReady(game: Game) = coroutineScope {
        _currentQuizState.emit(game.quiz)
        sendPush.setGameAccepted(game.fromUser, game.quiz.id)
    }
}