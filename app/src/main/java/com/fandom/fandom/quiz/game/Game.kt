package com.fandom.fandom.quiz.game

import com.fandom.fandom.quiz.fcm.send.Communication
import com.fandom.fandom.quiz.fcm.send.CommunicationType
import com.fandom.fandom.quiz.remoteDb.UserEntity
import kotlinx.serialization.Serializable


@Serializable
data class Game(val category: String,
                val questions: List<Question>,
                val fromUser:UserEntity,
                override val type: CommunicationType = CommunicationType.INVITATION_TO_GAME): Communication


@Serializable
data class Question(val questionId: String)
