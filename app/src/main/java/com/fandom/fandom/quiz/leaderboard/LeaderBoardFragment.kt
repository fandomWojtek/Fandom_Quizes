package com.fandom.fandom.quiz.leaderboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.FragmentLeaderboardBinding
import com.fandom.fandom.quiz.utils.safelyCollectFlow
import com.fandom.fandom.quiz.utils.viewBinding
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

class LeaderBoardFragment:Fragment(R.layout.fragment_leaderboard) {

    private val viewModel: LeaderBoardViewModel by viewModel()
    val binding by viewBinding(FragmentLeaderboardBinding::bind)
    private val adapter by lazy {
        UserListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getAllPlayers()
        binding.users.adapter = adapter
        binding.startQuizBattle.setOnClickListener {
            findNavController().navigate(R.id.action_leaderboardFragmentNav_to_chooseCategoryFragmentNav)
        }
        safelyCollectFlow(viewModel.allUsers) {
            adapter.submitList(it)
        }
    }
}

val leaderBoardModule = module {
    viewModel { LeaderBoardViewModel(get()) }
}