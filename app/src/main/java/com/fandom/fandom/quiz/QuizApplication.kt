package com.fandom.fandom.quiz

import android.app.Application
import com.fandom.fandom.quiz.auth.authModule
import com.fandom.fandom.quiz.auth.googleApiClientProvider
import com.fandom.fandom.quiz.networking.networkModule
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

private const val ONESIGNAL_APP_ID = "2fd8e624-7df4-432c-b00e-8d52cf449144"

class QuizApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@QuizApplication)
            modules(networkModule("url",true), authModule, googleApiClientProvider)
        }

        // Verbose Logging set to help debug issues, remove before releasing your app.
        OneSignal.Debug.logLevel = LogLevel.VERBOSE

        // OneSignal Initialization
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)

        // requestPermission will show the native Android notification permission prompt.
        // NOTE: It's recommended to use a OneSignal In-App Message to prompt instead.
        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }
    }
}