package com.fandom.fandom.quiz.remoteDb

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


const val USERS_COLLECTION = "users"
private const val TIME_OF_MINUTE = 1000 * 60

class UsersDb(private val firestore: FirebaseFirestore) {

    suspend fun getAllUsers() = firestore.collection(USERS_COLLECTION).get().await().map {
        UserEntity(
            userName = it.getString(USERNAME) ?: "",
            userPhoto = it.getString(USER_PHOTO) ?: "",
            lastActive = it.getTimestamp(LAST_ACTIVE)?.toDate()?.time ?: 0L,
            id = it.id,
            points = it.getLong(POINTS)?.toInt() ?: 0
        )
    }

    suspend fun addUser(user: UserEntity): UserEntity = user.copy(id = firestore.collection(USERS_COLLECTION).add(user.mapOfElements()).await().id)

    suspend fun getUserActiveInLastNMinutes(numberOfMinutes: Int = 5) = getAllUsers().filter {
        it.lastActive >= System.currentTimeMillis() - numberOfMinutes * TIME_OF_MINUTE
    }
}