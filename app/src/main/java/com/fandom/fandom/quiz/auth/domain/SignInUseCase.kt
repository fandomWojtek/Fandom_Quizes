package com.fandom.fandom.quiz.auth.domain

import com.fandom.fandom.quiz.remoteDb.UserEntity
import com.fandom.fandom.quiz.remoteDb.UsersDb
import com.onesignal.OneSignal

class SignInUseCase(private val usersDb: UsersDb, private val userRepository: UserRepository, private val oneSignal: OneSignal) {

    suspend fun signIn(user: UserEntity) {
        usersDb.addUser(user)
        userRepository.saveUser(user)
        oneSignal.login(user.id)
    }
}