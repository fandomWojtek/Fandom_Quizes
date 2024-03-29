package com.fandom.fandom.quiz.notification

import com.fandom.fandom.quiz.communication.CommunicationManager
import com.fandom.fandom.quiz.notification.send.*
import com.onesignal.notifications.INotificationReceivedEvent
import com.onesignal.notifications.INotificationServiceExtension
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotificationServiceExtension : INotificationServiceExtension,KoinComponent {
    private val notificationManager:CommunicationManager by inject()
    private val json: Json by inject()
    override fun onNotificationReceived(event: INotificationReceivedEvent) {
        val data =event.notification.additionalData
        data?.get("comType")?.let {
                when(CommunicationType.valueOf(it.toString())){
                    CommunicationType.INVITATION_TO_GAME ->{
                        val game = json.decodeFromString<Game>(data.toString())
                        notificationManager.receivedGameInvitation(game)
                    }
                    CommunicationType.ACCEPT_INVITATION -> {
                        val accept = json.decodeFromString<InvitationAccepted>(data.toString())
                        notificationManager.acceptInvitation(accept)
                    }
                    CommunicationType.START_GAME -> TODO()
                    CommunicationType.END_GAME -> TODO()
                    CommunicationType.QUESTION_RESPONSE -> {
                        val accept = json.decodeFromString<SendQuestionResponse>(data.toString())
                        notificationManager.questionResponse(accept)
                    }
                }
        }

    }
}