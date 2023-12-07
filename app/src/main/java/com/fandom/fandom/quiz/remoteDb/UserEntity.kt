package com.fandom.fandom.quiz.remoteDb

import kotlinx.serialization.Serializable
import kotlin.random.Random

internal const val USERNAME = "userName"
internal  const val USER_PHOTO = "userPhoto"
internal  const val LAST_ACTIVE = "lastActive"
internal  const val POINTS = "points"
private const val TIME_OF_MINUTE = 1000 * 60

@Serializable
data class UserEntity(
    val userName:String,
    val userPhoto:String = "",
    val lastActive:Long = 0L,
    val id:String,
    val points:Int = 0,
    val avatar:Int = Random.nextInt(12)
){
    fun mapOfElements() = mapOf(
        USERNAME to userName,
        USER_PHOTO to userPhoto,
        LAST_ACTIVE to lastActive,
        POINTS to points
    )

    fun isActive(numberOfMinutes: Int = 5) = lastActive >= System.currentTimeMillis() - numberOfMinutes * TIME_OF_MINUTE
}