package com.fandom.fandom.quiz.auth.domain

import android.content.SharedPreferences
import com.fandom.fandom.quiz.remoteDb.UserEntity
import kotlinx.serialization.encodeToString

import kotlinx.serialization.json.Json

const val USER_KEY = "SOME_USER_KEY"

class UserRepository(private var sharedPreferences: SharedPreferences, private val json: Json, val key: String = USER_KEY) {

    suspend fun isUserLoggedIn() = sharedPreferences.contains(key)

    fun saveUser(user: UserEntity) {
        sharedPreferences.edit().putString(key, json.encodeToString(user)).apply()
    }

    suspend fun getUser(): UserEntity? {
        val user = sharedPreferences.getString(key, null)
        return if (user != null) json.decodeFromString(user) else null
    }
}