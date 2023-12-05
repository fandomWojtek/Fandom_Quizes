package com.fandom.fandom.quiz.fcm

import android.util.Log

class QuizFirebaseService : com.google.firebase.messaging.FirebaseMessagingService() {

    override fun onNewToken(token: String) {
//        Log.d(TAG, "Refreshed token: $token")
//
//        // If you want to send messages to this application instance or
//        // manage this apps subscriptions on the server side, send the
//        // FCM registration token to your app server.
//        sendRegistrationToServer(token)
    }
}