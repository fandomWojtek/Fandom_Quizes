package com.fandom.fandom.quiz.notification

import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await


suspend fun getToken()  = FirebaseMessaging.getInstance().token.await() ?: "null"