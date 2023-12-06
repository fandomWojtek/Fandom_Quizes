package com.fandom.fandom.quiz.notification.send

import com.fandom.fandom.quiz.quiz.api.Quiz
import com.fandom.fandom.quiz.remoteDb.UserEntity
import kotlinx.serialization.Serializable

enum class CommunicationType {
    INVITATION_TO_GAME, ACCEPT_INVITATION, REJECT_INVITATION, START_GAME, END_GAME, QUESTION_RESPONSE
}


@Serializable
sealed interface Communication {
    val comType: CommunicationType
}

@Serializable
data class Game(
    val category: String,
    val quiz: Quiz,
    val fromUser: UserEntity,
    override val comType: CommunicationType = CommunicationType.INVITATION_TO_GAME
) : Communication

@Serializable
data class InvitationAccepted(
    val fromUserEntity: UserEntity,
    val forQuiz: Int,
    val accepted: Boolean = false,
    override val comType: CommunicationType = CommunicationType.ACCEPT_INVITATION
) : Communication