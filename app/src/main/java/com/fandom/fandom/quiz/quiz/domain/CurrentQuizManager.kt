package com.fandom.fandom.quiz.quiz.domain

import com.fandom.fandom.quiz.auth.domain.CurrentUserRepository
import com.fandom.fandom.quiz.categories.categoryList
import com.fandom.fandom.quiz.communication.CommunicationManager
import com.fandom.fandom.quiz.notification.send.*
import com.fandom.fandom.quiz.quiz.api.Quiz
import com.fandom.fandom.quiz.quiz.presentation.OpponentResponses
import com.fandom.fandom.quiz.quiz.presentation.QuestionResponse
import com.fandom.fandom.quiz.remoteDb.UserEntity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*

data class QuizFinish(val myResponses: List<QuestionResponse>, val opponentResponses: List<QuestionResponse>, val opponent: String)
class CurrentQuizManager(
    private val loadQuizUseCase: LoadQuizUseCase,
    private val sendPush: SendPush,
    private val userRepository: CurrentUserRepository,
    private val communicationManager: CommunicationManager
) {

    private val _currentQuizState: MutableStateFlow<Quiz?> = MutableStateFlow(null)
    val currentQuizState: StateFlow<Quiz?> = _currentQuizState

    private val _isCurrentHost: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isCurrentHost: StateFlow<Boolean> = _isCurrentHost

    private val _currentOpponent: MutableStateFlow<String> = MutableStateFlow("")
    val currentOpponent: StateFlow<String> = _currentOpponent

    suspend fun loadQuizAndInviteUserToIt(userToInvite: UserEntity, siteId: String): Boolean {
        val quiz = loadQuizUseCase.loadQuiz(siteId)
        val currentUser = userRepository.getCurrentUser()!!
        sendPush.sendInvitationToGame(userToInvite, Game(categoryList.find { it.id == siteId }!!.name, quiz.toQuizMetadata(), currentUser.id))
        var accepted: Boolean = false
        communicationManager.acceptInvitation.takeWhile {
            accepted = it.accepted
            !(it.forQuiz == quiz.id && it.fromUserEntity == currentUser.id)
        }.collect()
        _currentQuizState.emit(quiz)
        _isCurrentHost.emit(accepted)
        if (accepted) _currentOpponent.emit(userToInvite.id) else _currentOpponent.emit("")
        return accepted
    }

    suspend fun acceptInvitationAndSendInfoThatYouAreReady(game: Game) = coroutineScope {
        val categoryId = categoryList.find { it.name == game.category }?.id ?: ""
        val quiz = loadQuizUseCase.loadQuizWithQuestionIds(categoryId, game.quiz.quizId, game.quiz.questions)
        _currentQuizState.emit(quiz)
        _currentOpponent.emit(game.fromUser)
        sendPush.setGameAccepted(game.fromUser, game.quiz.quizId, true)
        _isCurrentHost.emit(false)
    }

    suspend fun rejectInvitation(game: Game) = coroutineScope {
        _currentQuizState.emit(null)
        sendPush.setGameAccepted(game.fromUser, game.quiz.quizId, false)
    }

    private val _currentOpponentResponses: MutableSharedFlow<OpponentResponses> = MutableSharedFlow(replay = 0)
    val currentOpponentResponses: SharedFlow<OpponentResponses> = _currentOpponentResponses

    private val _currentUserResponses: MutableSharedFlow<OpponentResponses> = MutableSharedFlow(replay = 0)
    val currentUserResponses: SharedFlow<OpponentResponses> = _currentUserResponses


    private val _quizFinished: MutableStateFlow<QuizFinish?> = MutableStateFlow(null)
    val quizFinished: StateFlow<QuizFinish?> = _quizFinished

    suspend fun persistCurrentOpponentResponses(responses: OpponentResponses) {
        _currentOpponentResponses.emit(responses)
    }


    suspend fun sendQuestionResponse(questions: OpponentResponses) {
        _currentUserResponses.emit(questions)
        sendPush.sendQuestionResponse(
            _currentOpponent.value,
            SendQuestionResponse(userRepository.getCurrentUser()?.id ?: "", _currentOpponent.value, questions.list.map { if (it.correct) 1 else 0 }, questions.list.map { it.time })
        )
    }

    suspend fun gatherResponses() {
        _quizFinished.emit(QuizFinish(_currentUserResponses.replayCache.first().list, _currentOpponentResponses.replayCache.first().list, _currentOpponent.value))
    }

    suspend fun clearCurrentQuiz() {
        _currentQuizState.value = null
        _isCurrentHost.value = false
        _currentOpponent.value = ""
        _currentOpponentResponses.emit(OpponentResponses(emptyList()))
        _quizFinished.emit(null)
    }
}