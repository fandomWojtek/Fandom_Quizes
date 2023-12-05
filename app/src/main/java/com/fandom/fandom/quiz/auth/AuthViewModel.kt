package com.fandom.fandom.quiz.auth

import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


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

