package com.fandom.fandom.quiz.landing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.LandingScreenFragmentBinding
import com.fandom.fandom.quiz.utils.safelyCollectFlow
import com.fandom.fandom.quiz.utils.viewBinding
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

class LandingScreenFragment : Fragment(R.layout.landing_screen_fragment) {

    private val viewModel: DecideWhereToGoViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        safelyCollectFlow(viewModel.goToLeaderBoard) {
            findNavController().navigate(R.id.action_landingScreenNav_to_leaderboardFragmentNav)
        }
        safelyCollectFlow(viewModel.goToLogin) {
            findNavController()
                .navigate(R.id.action_landingScreenNav_to_authFragmentNav)
        }

        viewModel.checkWhereToGo()
    }
}

val landingModule = module {
    viewModel { DecideWhereToGoViewModel(get()) }
}