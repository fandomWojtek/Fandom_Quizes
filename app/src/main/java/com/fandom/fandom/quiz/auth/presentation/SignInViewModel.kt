package com.fandom.fandom.quiz.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.auth.domain.SignInUseCase
import com.fandom.fandom.quiz.remoteDb.UserEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class SignInViewModel(private val useCase: SignInUseCase) : ViewModel() {

    private val _userLoggedIn: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0)
    val userLoggedIn: SharedFlow<Unit> = _userLoggedIn
    private val _login: MutableStateFlow<String> = MutableStateFlow("")
    val login: StateFlow<String> = _login

    val canSignIn: Flow<Boolean> = _login.map { it.isNotBlank() }
    fun signIn() {
        viewModelScope.launch {
            if (login.value.isNullOrBlank()) return@launch
            useCase.signIn(UserEntity(userName = login.value, userPhoto = "", lastActive = Date().time, id = "", points = 0))
            _userLoggedIn.emit(Unit)
        }
    }

    fun setUpLogin(login: String) {
        viewModelScope.launch {
            _login.value = login
        }

    }
}