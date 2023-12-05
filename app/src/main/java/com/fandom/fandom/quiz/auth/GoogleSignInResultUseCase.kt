package com.fandom.fandom.quiz.auth

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn

internal class GoogleSignInResultUseCase {
    fun parse(data: Intent?): SocialUserDetails? {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val signInAccount = task.result
            if (task.isSuccessful && signInAccount != null) {
                val name = signInAccount.displayName
                val email = signInAccount.email
                val idToken = signInAccount.idToken
                if (name != null && email != null && idToken != null) {
                    return SocialUserDetails(idToken, name = name, email = email, socialNetwork = SocialNetwork.GOOGLE)
                }
            }
            return null
        } catch (ex: Exception) {
            return null
        }
    }
}