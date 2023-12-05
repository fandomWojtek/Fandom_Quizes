package com.fandom.fandom.quiz.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fandom.fandom.quiz.auth.domain.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DecideWhereToGoViewModel(private val userRepository: UserRepository):ViewModel() {


    private val _goToLogin : MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0 )
    val goToLogin : SharedFlow<Unit> =  _goToLogin

    private val _goToLeaderBoard : MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0 )
    val goToLeaderBoard : SharedFlow<Unit> =  _goToLeaderBoard


    fun checkWhereToGo(){
        viewModelScope.launch {
            delay(500)
            if(userRepository.isUserLoggedIn()){
                _goToLeaderBoard.emit(Unit)
            }else{
                _goToLogin.emit(Unit)
            }
        }
    }
}