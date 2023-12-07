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

    private val avatarsDrawable by lazy {
        this.requireContext().avatars()
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

        safelyCollectFlow(viewModel.top3Users) {
            it.user1?.let { binding.user1.setImageDrawable( avatarsDrawable[it.avatar]) }
            it.user2?.let { binding.user2.setImageDrawable( avatarsDrawable[it.avatar]) }
            it.user3?.let { binding.user3.setImageDrawable( avatarsDrawable[it.avatar]) }
        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllPlayers()
    }
}

val leaderBoardModule = module {
    viewModel { LeaderBoardViewModel(get(),get()) }
}