package com.fandom.fandom.quiz.quiz.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType

class QuizRequestApi(private val httpClient: HttpClient) {
    suspend fun getQuiz(siteId: String): QuizResponse = httpClient.get("https://services.fandom.com/quizzes/targeting/find") {
        parameter("siteId", siteId)
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
    }.body()
}