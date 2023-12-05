package com.fandom.fandom.quiz.remoteDb

import kotlinx.serialization.Serializable

internal const val USERNAME = "userName"
internal  const val USER_PHOTO = "userPhoto"
internal  const val LAST_ACTIVE = "lastActive"
internal  const val POINTS = "points"

@Serializable
data class UserEntity(
    val userName:String,
    val userPhoto:String = "",
    val lastActive:Long = 0L,
    val id:String,
    val points:Int = 0
){
    fun mapOfElements() = mapOf(
        USERNAME to userName,
        USER_PHOTO to userPhoto,
        LAST_ACTIVE to lastActive,
        POINTS to points
    )
}