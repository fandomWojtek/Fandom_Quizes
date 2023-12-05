package com.fandom.fandom.quiz.remoteDb

import com.fandom.fandom.quiz.auth.domain.UserRepository

class UpdateCurrentUserLastInteractionTimeUseCase(private val userRepository: UserRepository,private val usersDb: UsersDb) {
    suspend fun updateCurrentUserLastInteractionTime() {
        val user = userRepository.getUser() ?: return
        usersDb.updateCurrentTimeStampForUser(user.id)
    }
}