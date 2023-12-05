package com.fandom.fandom.quiz.notification.send

enum class CommunicationType  {
    INVITATION_TO_GAME,ACCEPT_INVITATION,REJECT_INVITATION,START_GAME,END_GAME,QUESTION_RESPONSE
}

interface Communication {
    val type: CommunicationType
}