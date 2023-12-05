package com.fandom.fandom.quiz.fcm.send

import com.fandom.fandom.quiz.auth.domain.UserRepository
import com.fandom.fandom.quiz.game.Game
import com.fandom.fandom.quiz.remoteDb.UserEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType

class SendPush(private val httpApiClient: HttpClient,private val currentUserRepository: UserRepository) {

    private val restApiOneSignalKey = "YWI1ZGMzNmItNGI1NS00M2E5LWFkNTUtYjNmYWVlYTk5NTU2"

    init {

    }

    suspend fun sendInvitationToGame(toUser: UserEntity, game: Game) {
        val request = AppNotification(includeAliases = IncludeAliases(listOf(toUser.id)), customData = game)
        httpApiClient.post {
            header("Authorization", restApiOneSignalKey)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(request)
        }
    }

}

