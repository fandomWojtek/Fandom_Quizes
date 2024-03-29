package com.fandom.fandom.quiz.remoteDb

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


const val USERS_COLLECTION = "users"

class UsersDb(private val firestore: FirebaseFirestore) {

    suspend fun getAllUsers() = firestore.collection(USERS_COLLECTION).get().await().map {
        UserEntity(
            userName = it.getString(USERNAME) ?: "",
            userPhoto = it.getString(USER_PHOTO) ?: "",
            lastActive = it.getLong(LAST_ACTIVE)?: 0L,
            id = it.id,
            points = it.getLong(POINTS)?.toInt() ?: 0
        )
    }

    suspend fun addUser(user: UserEntity): UserEntity = user.copy(id = firestore.collection(USERS_COLLECTION).add(user.mapOfElements()).await().id)

    suspend fun getUserActiveInLastNMinutes(numberOfMinutes: Int = 5) = getAllUsers().filter {
       it.isActive(numberOfMinutes)
    }

    suspend fun updateCurrentTimeStampForUser(id:String) {
        firestore.collection(USERS_COLLECTION).document(id).update(LAST_ACTIVE, System.currentTimeMillis()).await()
    }

    suspend fun getUserById(id:String):UserEntity?{
        val user = firestore.collection(USERS_COLLECTION).document(id).get().await()
        return if(user.exists()){
            UserEntity(
                userName = user.getString(USERNAME) ?: "",
                userPhoto = user.getString(USER_PHOTO) ?: "",
                lastActive = user.getLong(LAST_ACTIVE)?: 0L,
                id = user.id,
                points = user.getLong(POINTS)?.toInt() ?: 0
            )
        }else{
            null
        }
    }

    suspend fun updateUser(entity: UserEntity) {
        firestore.collection(USERS_COLLECTION).document(entity.id).update(POINTS, entity.points).await()
    }
}