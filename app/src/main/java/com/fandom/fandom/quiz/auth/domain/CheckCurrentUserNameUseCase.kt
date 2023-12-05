package com.fandom.fandom.quiz.auth.domain


import com.fandom.fandom.quiz.remoteDb.UsersDb
data class SignInState(val canSignIn: Boolean = false,val reason:String = "")
class CheckCurrentUserNameUseCase(private val usersDb: UsersDb) {
    suspend fun checkCurrentUserName(userName: String): SignInState = when {
        userName.isBlank() -> SignInState(false,"Empty user name")
        usersDb.getAllUsers().any { it.userName == userName } -> SignInState(false,"User name already exists")
        else -> SignInState(true)
    }


}
