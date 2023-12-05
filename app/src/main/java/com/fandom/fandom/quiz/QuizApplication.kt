package com.fandom.fandom.quiz

import android.app.Application
import com.fandom.fandom.quiz.auth.authModule
import com.fandom.fandom.quiz.auth.googleApiClientProvider
import com.fandom.fandom.quiz.networking.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class QuizApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@QuizApplication)
            modules(networkModule("url",true), authModule, googleApiClientProvider)
        }
    }
}