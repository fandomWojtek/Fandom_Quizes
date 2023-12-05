package com.fandom.fandom.quiz.fcm.send

import com.fandom.fandom.quiz.game.Game
import com.fandom.fandom.quiz.remoteDb.UserEntity
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import io.ktor.client.HttpClient

class SendPush(private val messaging: FirebaseMessaging,private val httpApiClient: HttpClient) {
    init {
        "https://fcm.googleapis.com/v1/fandom-quiz-98279"
    }

    suspend fun sendInvitationToGame(userEntity: UserEntity,game:Game){
        val remoteMessage:RemoteMessage = RemoteMessage.Builder(SERVER_KEY)
            .setMessageId(userEntity.id)
            .addData("game",game.toString())
            .build()
//        messaging.send()
    }

}