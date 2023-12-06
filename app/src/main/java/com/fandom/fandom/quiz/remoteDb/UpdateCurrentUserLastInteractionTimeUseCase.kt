package com.fandom.fandom.quiz.remoteDb

import com.fandom.fandom.quiz.auth.domain.CurrentUserRepository

class UpdateCurrentUserLastInteractionTimeUseCase(private val userRepository: CurrentUserRepository, private val usersDb: UsersDb) {
    suspend fun updateCurrentUserLastInteractionTime() {
        val user = userRepository.getCurrentUser() ?: return
        usersDb.updateCurrentTimeStampForUser(user.id)
    }
}