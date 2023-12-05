package com.fandom.fandom.quiz.auth

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.auth.domain.*
import com.fandom.fandom.quiz.auth.presentation.SignInViewModel
import com.fandom.fandom.quiz.databinding.AuthScreenFragmentBinding
import com.fandom.fandom.quiz.utils.safelyCollectFlow
import com.fandom.fandom.quiz.utils.viewBinding
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

class AuthFragment : Fragment(R.layout.auth_screen_fragment) {


    private val viewModel: SignInViewModel by viewModel()
    val binding by viewBinding(AuthScreenFragmentBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        safelyCollectFlow(viewModel.canSignIn) {
            binding.signInButton.isEnabled = it.canSignIn
            binding.errorMessage.isVisible = it.reason.isNotBlank()
            binding.errorMessage.text = it.reason
        }
        safelyCollectFlow(viewModel.userLoggedIn) {
            findNavController().navigate(R.id.action_authFragmentNav_to_leaderboardFragmentNav)
        }

        binding.userName.addTextChangedListener {
            if (it != null) {
                viewModel.setUpLogin(it.toString())
            }
        }
        binding.signInButton.setOnClickListener {
            viewModel.signIn()
        }

    }

}


val authModule = module {
    single { UserRepository(get(), get()) }
    factory { SignInUseCase(get(), get(), get()) }
    factory { CheckCurrentUserNameUseCase(get()) }
    viewModel<SignInViewModel> { SignInViewModel(get(), get()) }
}