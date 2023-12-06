package com.fandom.fandom.quiz.quiz.api


import com.fandom.fandom.quiz.notification.send.QuizMetaData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizResponse(
    @SerialName("quizzes")
    val quizzes: List<Quiz>
)

@Serializable
data class Quiz(
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("questions")
    val questions: List<Question>,
    @SerialName("siteId")
    val siteId: String,
    @SerialName("title")
    val title: String
) {
    fun toQuizMetadata(): QuizMetaData = QuizMetaData(id, questions.map { it.id })
}

@Serializable
data class Question(
    @SerialName("answers")
    val answers: List<Answer>,
    @SerialName("id")
    val id: String,
    @SerialName("text")
    val text: String,
    @SerialName("type")
    val type: String
)

@Serializable
data class Answer(
    @SerialName("isCorrect")
    val isCorrect: Boolean,
    @SerialName("reference")
    val reference: String,
    @SerialName("text")
    val text: String
)