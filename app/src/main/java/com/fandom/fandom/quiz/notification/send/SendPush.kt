package com.fandom.fandom.quiz.notification.send

import android.system.Os.accept
import com.fandom.fandom.quiz.auth.domain.UserRepository
import com.fandom.fandom.quiz.communication.InvitationAccepted
import com.fandom.fandom.quiz.game.Game
import com.fandom.fandom.quiz.remoteDb.UserEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType

class SendPush(private val httpApiClient: HttpClient) {

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

    suspend fun setGameAccepted(toUser: UserEntity, quizId: Int) {
        val request = AppNotification(includeAliases = IncludeAliases(listOf(toUser.id)), customData = InvitationAccepted(toUser, quizId))
        httpApiClient.post {
            header("Authorization", restApiOneSignalKey)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(request)
        }
    }

}

