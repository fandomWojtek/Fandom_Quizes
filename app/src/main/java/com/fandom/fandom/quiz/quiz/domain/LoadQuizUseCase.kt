package com.fandom.fandom.quiz.quiz.domain

import com.fandom.fandom.quiz.quiz.api.*

class LoadQuizUseCase(private val quizRequestApi: QuizRequestApi) {

    suspend fun loadQuiz(siteId: String): Quiz {
        val random = quizRequestApi.getQuiz(siteId).quizzes.random()
        return random.copy(questions = random.questions.filter { it.type== "text" }.take(5))
    }
}