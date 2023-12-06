package com.fandom.fandom.quiz

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.fandom.fandom.quiz.databinding.MainActivityBinding
import com.fandom.fandom.quiz.quiz.presentation.AwaitQuizInvitationViewModel
import com.fandom.fandom.quiz.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    private val binding by viewBinding(MainActivityBinding::inflate)


    private val navController: NavController
        get() = Navigation.findNavController(binding.quizNavHost)

    private val awaitViewModel: AwaitQuizInvitationViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportFragmentManager.findFragmentByTag(NavHostFragment::class.java.name) == null) {
            val finalHost = NavHostFragment.create(R.navigation.quiz_navigation)
            supportFragmentManager.beginTransaction()
                .replace(R.id.quizNavHost, finalHost, NavHostFragment::class.java.name)
                .setPrimaryNavigationFragment(finalHost)
                .commit()
        }


        safelyCollectFlow(awaitViewModel.invitationIncoming){
            showQuestionDialog(getString(R.string.would_you_like_to_play_with, it.fromUser.userName,it.game.category), firstButtonAction = {
                awaitViewModel.respondToInvitation(it.game,true)
            }, secondButtonAction = {
                awaitViewModel.respondToInvitation(it.game,false)
            })
        }

        safelyCollectFlow(awaitViewModel.goToQuizScreen){
            navController.navigate(R.id.action_global_quizScreenNav)
        }
    }
}