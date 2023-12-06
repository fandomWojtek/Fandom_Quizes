package com.fandom.fandom.quiz.game

import com.fandom.fandom.quiz.notification.send.Communication
import com.fandom.fandom.quiz.notification.send.CommunicationType
import com.fandom.fandom.quiz.quiz.api.Quiz
import com.fandom.fandom.quiz.remoteDb.UserEntity
import kotlinx.serialization.Serializable


@Serializable
data class Game(val category: String,
                val quiz: Quiz,
                val fromUser:UserEntity,
                override val type: CommunicationType = CommunicationType.INVITATION_TO_GAME): Communication


