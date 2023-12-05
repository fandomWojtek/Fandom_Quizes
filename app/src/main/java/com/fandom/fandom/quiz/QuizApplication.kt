package com.fandom.fandom.quiz

import android.app.Application
import android.content.Context
import com.fandom.fandom.quiz.auth.authModule
import com.fandom.fandom.quiz.landing.landingModule
import com.fandom.fandom.quiz.leaderboard.leaderBoardModule
import com.fandom.fandom.quiz.networking.networkModule
import com.fandom.fandom.quiz.remoteDb.UsersDb
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import com.onesignal.notifications.INotificationLifecycleListener
import com.onesignal.notifications.INotificationWillDisplayEvent
import kotlinx.coroutines.*
import org.koin.android.ext.koin.*
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

private const val ONESIGNAL_APP_ID = "2fd8e624-7df4-432c-b00e-8d52cf449144"

class QuizApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@QuizApplication)
            modules(networkModule("url", true), authModule,appModule,landingModule,leaderBoardModule)
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

val appModule = module {
    single { OneSignal }
    single { UsersDb(Firebase.firestore) }
    single { androidApplication().getSharedPreferences("shared", Context.MODE_PRIVATE) }
}