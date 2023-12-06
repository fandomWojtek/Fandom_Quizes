package com.fandom.fandom.quiz.quiz.domain

import com.fandom.fandom.quiz.quiz.api.*

class LoadQuizUseCase(private val quizRequestApi: QuizRequestApi) {

    suspend fun loadQuiz(siteId: String): Quiz {
        val random = quizRequestApi.getQuiz(siteId).quizzes.random()
        return random.copy(questions = random.questions.filter { it.type == "text" }.take(5))
    }

    suspend fun loadQuizWithQuestionIds(siteId: String,quizId:Int, questionIds: List<String>): Quiz {
        val random = quizRequestApi.getQuiz(siteId).quizzes.find { it.id==quizId }!!
        return random.copy(questions = random.questions.filter { it.type == "text" }.filter { it.id in questionIds })
    }
}