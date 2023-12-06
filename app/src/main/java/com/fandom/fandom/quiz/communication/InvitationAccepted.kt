package com.fandom.fandom.quiz.communication

import com.fandom.fandom.quiz.notification.send.Communication
import com.fandom.fandom.quiz.notification.send.CommunicationType
import com.fandom.fandom.quiz.remoteDb.UserEntity
import kotlinx.serialization.Serializable


@Serializable
data class InvitationAccepted(val fromUserEntity: UserEntity, val forQuiz: String, override val type: CommunicationType = CommunicationType.ACCEPT_INVITATION) : Communication