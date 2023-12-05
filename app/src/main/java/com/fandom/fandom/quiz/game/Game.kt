package com.fandom.fandom.quiz.game

import kotlinx.serialization.Serializable


@Serializable
data class Game(val category: String, val questions: List<Question>)


@Serializable
data class Question(val questionId: String)
