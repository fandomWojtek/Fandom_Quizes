package com.fandom.fandom.quiz.opponent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.FragmentChooseOponentBinding
import com.fandom.fandom.quiz.utils.safelyCollectFlow
import com.fandom.fandom.quiz.utils.viewBinding
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class ChooseOpponentFragment : Fragment(R.layout.fragment_choose_oponent) {
    private val categoryId by lazy {
        requireArguments().getString("categoryId")
    }

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
        safelyCollectFlow(viewModel.activeUsers) {
            if (it.isEmpty()) {
                updateView(showEmptyState = true)
            } else {
                updateView(showEmptyState = false)
                adapter.submitList(it)
            }
        }
        safelyCollectFlow(viewModel.waitForUserRespond){

        }
    }

    private fun updateView(showEmptyState: Boolean) {
        if (showEmptyState) {
            binding.run {
                emptyState.visibility = View.VISIBLE
                opponents.visibility = View.GONE
            }
        } else {
            binding.run {
                emptyState.visibility = View.GONE
                opponents.visibility = View.VISIBLE
            }
        }
    }
}

val opponentModule = module {
    viewModel { params -> ChooseOponentViewModel(get(), get(),get(), params[0]) }
}