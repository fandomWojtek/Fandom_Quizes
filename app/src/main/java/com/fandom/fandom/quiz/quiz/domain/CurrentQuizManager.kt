package com.fandom.fandom.quiz.quiz.domain

import com.fandom.fandom.quiz.auth.domain.UserRepository
import com.fandom.fandom.quiz.categories.categoryList
import com.fandom.fandom.quiz.game.Game
import com.fandom.fandom.quiz.notification.send.SendPush
import com.fandom.fandom.quiz.quiz.api.Quiz
import com.fandom.fandom.quiz.remoteDb.UserEntity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.C

class CurrentQuizManager(private val loadQuizUseCase: LoadQuizUseCase, private val sendPush: SendPush, private val userRepository: UserRepository) {

    private val _currentQuizState: MutableStateFlow<Quiz?> = MutableStateFlow(null)
    val currentQuizState: StateFlow<Quiz?> = _currentQuizState
    suspend fun loadQuizAndInviteUserToIt(userEntity: UserEntity, siteId: String) = coroutineScope {
        val quiz = loadQuizUseCase.loadQuiz(siteId)
        launch {  }
        sendPush.sendInvitationToGame(userEntity, Game(categoryList.find { it.id==siteId }!!.name,quiz,userRepository.getUser()!!))
    }
}