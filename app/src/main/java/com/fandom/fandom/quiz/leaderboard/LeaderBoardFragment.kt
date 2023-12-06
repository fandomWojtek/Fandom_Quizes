package com.fandom.fandom.quiz.leaderboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.FragmentLeaderboardBinding
import com.fandom.fandom.quiz.quiz.presentation.AwaitQuizInvitationViewModel
import com.fandom.fandom.quiz.utils.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

class LeaderBoardFragment : Fragment(R.layout.fragment_leaderboard) {

    private val viewModel: LeaderBoardViewModel by viewModel()

    val binding by viewBinding(FragmentLeaderboardBinding::bind)
    private val adapter by lazy {
        UserListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        UpdateUserActivity(this)
        binding.users.adapter = adapter
        binding.startQuizBattle.setOnClickListener {
            findNavController().navigate(R.id.action_leaderboardFragmentNav_to_chooseCategoryFragmentNav)
        }
        safelyCollectFlow(viewModel.allUsers) {
            adapter.submitList(it)
        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllPlayers()
    }
}

val leaderBoardModule = module {
    viewModel { LeaderBoardViewModel(get()) }
}