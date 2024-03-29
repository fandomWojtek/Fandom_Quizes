package com.fandom.fandom.quiz.opponent

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.FragmentChooseOponentBinding
import com.fandom.fandom.quiz.utils.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class ChooseOpponentFragment : Fragment(R.layout.fragment_choose_oponent) {
    private val categoryId by lazy {
        requireArguments().getString("categoryId")
    }

    private val moveInsetsHandler: MoveInsetsHandler by inject()
    private val viewModel: ChooseOponentViewModel by viewModel { parametersOf(categoryId) }
    val binding by viewBinding(FragmentChooseOponentBinding::bind)
    private val adapter by lazy {
        OpponentListAdapter {
            viewModel.inviteUser(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getOpponents()
        binding.opponents.adapter = adapter
        view.findViewById<View>(R.id.backButton).setOnClickListener {
            findNavController().popBackStack()
        }
        view.findViewById<TextView>(R.id.toolbarTitle).text = getString(R.string.choose_opponent)
        safelyCollectFlow(viewModel.activeUsers) {
            if (it.isEmpty()) {
                updateView(showEmptyState = true)
            } else {
                updateView(showEmptyState = false)
                adapter.submitList(it)
            }
        }
        safelyCollectFlow(viewModel.waitForUserRespond){
            binding.waitingView.isVisible = it
        }
        safelyCollectFlow(viewModel.goToQuiz){
            findNavController().navigate(R.id.action_chooseOponentFragmentNav_to_quizScreenNav)
        }
        safelyCollectFlow(viewModel.userDeclinedYourInvite){
            showSimpleDialog(getString(R.string.user_declined_invite))
        }
    }

    private fun updateView(showEmptyState: Boolean) {
        binding.emptyState.isVisible = showEmptyState
        binding.opponents.isVisible = !showEmptyState
    }

    override fun onResume() {
        moveInsetsHandler.handleInsetsToMoveUnderStatusBar(this)
        super.onResume()
    }

    override fun onPause() {
        moveInsetsHandler.handleInsetsToMoveToNormalPosition(this)
        super.onPause()
    }
}

val opponentModule = module {
    viewModel { params -> ChooseOponentViewModel(get(), get(),get(), params[0]) }
}