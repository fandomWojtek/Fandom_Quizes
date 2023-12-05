package com.fandom.fandom.quiz.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.auth.domain.*
import com.fandom.fandom.quiz.remoteDb.UserEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Date


class SignInViewModel(private val useCase: SignInUseCase, private val checkCurrentUserNameUseCase: CheckCurrentUserNameUseCase) : ViewModel() {

    private val _userLoggedIn: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0)
    val userLoggedIn: SharedFlow<Unit> = _userLoggedIn
    private val _login: MutableStateFlow<String> = MutableStateFlow("")


    val canSignIn: Flow<SignInState> = _login.map { checkCurrentUserNameUseCase.checkCurrentUserName(it) }


    fun signIn() {
        viewModelScope.launch {
            if (_login.value.isBlank()) return@launch
            useCase.signIn(UserEntity(_login.value, "", Date().time, "", 0))
            _userLoggedIn.emit(Unit)
        }
    }

    fun setUpLogin(login: String) {
        viewModelScope.launch {
            _login.value = login
        }

    }
}