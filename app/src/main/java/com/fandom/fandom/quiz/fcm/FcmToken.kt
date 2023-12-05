package com.fandom.fandom.quiz.fcm

import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await


suspend fun getToken()  = FirebaseMessaging.getInstance().token.await() ?: "null"