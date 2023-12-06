package com.fandom.fandom.quiz.summary

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.FragmentSummaryBinding
import com.fandom.fandom.quiz.utils.safelyCollectFlow
import com.fandom.fandom.quiz.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SummaryFragment : Fragment(R.layout.fragment_summary) {

    private val viewModel: SummaryViewModel by viewModel()
    val binding by viewBinding(FragmentSummaryBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getUsersData()
        viewModel.summarizeTheQuiz()

        safelyCollectFlow(viewModel.opponentData) {
            it?.let {
                binding.run {
                    opponentUserName.text = it.userName
                    opponentAvatar.setImageDrawable(ContextCompat.getDrawable(opponentAvatar.context, R.drawable.avatar01))
                }
            }
        }

        safelyCollectFlow(viewModel.currentUserData) {
            it?.let {
                binding.run {
                    hostUserName.text = it.userName
                    hostAvatar.setImageDrawable(ContextCompat.getDrawable(opponentAvatar.context, R.drawable.avatar02))
                }
            }
        }

        safelyCollectFlow(viewModel.currentUserPoints) {
            binding.hostPoints.text = it.toString()
        }

        safelyCollectFlow(viewModel.opponentPoints) {
            binding.opponentPoints.text = it.toString()
        }
    }
}