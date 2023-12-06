package com.fandom.fandom.quiz.quiz

import com.fandom.fandom.quiz.quiz.api.QuizRequestApi
import com.fandom.fandom.quiz.quiz.domain.CurrentQuizManager
import com.fandom.fandom.quiz.quiz.domain.LoadQuizUseCase
import com.fandom.fandom.quiz.quiz.presentation.AwaitQuizInvitationViewModel
import com.fandom.fandom.quiz.quiz.presentation.QuizViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val quizModule = module {
    factory { QuizRequestApi(get()) }
    factory { LoadQuizUseCase(get()) }
    single { CurrentQuizManager(get(),get(),get(),get()) }
    viewModel{ AwaitQuizInvitationViewModel(get(),get()) }
    viewModel { QuizViewModel(get()) }
}