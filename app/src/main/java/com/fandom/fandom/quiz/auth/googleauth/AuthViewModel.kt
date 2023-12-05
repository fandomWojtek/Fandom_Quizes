package com.fandom.fandom.quiz.auth.googleauth

import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.auth.googleauth.GoogleSignInResultUseCase
import com.fandom.fandom.quiz.auth.googleauth.SocialUserDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AuthViewModel internal constructor(private val googleUseCase: GoogleSignInResultUseCase): ViewModel() {

    val dispatcherProvider: CoroutineDispatcher = Dispatchers.Default

    fun googleLogin(activityResult: ActivityResult) {
        viewModelScope.launch {
            withContext(dispatcherProvider) {
                val socialUserDetails = googleUseCase.parse(activityResult.data)
                if (socialUserDetails == null) {
                    // error
                } else {
                    handleSocialLogin(socialUserDetails)
                }
            }
        }
    }

    private suspend fun handleSocialLogin(details: SocialUserDetails) {
        // todo
    }


}

