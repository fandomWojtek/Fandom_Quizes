package com.fandom.fandom.quiz.notification.send

import com.fandom.fandom.quiz.remoteDb.UserEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType

class SendPush(private val httpApiClient: HttpClient) {

    private val restApiOneSignalKey = "Basic YWI1ZGMzNmItNGI1NS00M2E5LWFkNTUtYjNmYWVlYTk5NTU2"

    init {

    }

    private val oneSignalUrl = "https://onesignal.com/api/v1/notifications"
    private val appId =  "2fd8e624-7df4-432c-b00e-8d52cf449144"
    private val channel: String = "push"

    suspend fun sendInvitationToGame(toUser: UserEntity, game: Game) {
        val request = AppNotification(includeAliases = IncludeAliases(listOf(toUser.id)), customData = game, appId = appId, targetChannel = channel)

        httpApiClient.post(oneSignalUrl) {
            header("Authorization", restApiOneSignalKey)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(request)
        }
    }

    suspend fun setGameAccepted(toUser: String, quizId: Int,accepted:Boolean = false) {
        val request = AppNotification(includeAliases = IncludeAliases(listOf(toUser)), customData = InvitationAccepted(toUser, quizId,accepted = accepted), appId = appId, targetChannel = channel)
        httpApiClient.post(oneSignalUrl) {
            header("Authorization", restApiOneSignalKey)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(request)
        }
    }

}


