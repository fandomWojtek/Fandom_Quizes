package com.fandom.fandom.quiz.summary

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

        binding.run {
            startAnimation(vs, 1)
            startAnimation(finishButton, 1)
            finishButton.setOnClickListener { findNavController().popBackStack() }
        }

        safelyCollectFlow(viewModel.opponentData) {
            it?.let {
                binding.run {
                    opponentUserName.text = it.userName
                    opponentAvatar.setImageDrawable(ContextCompat.getDrawable(opponentAvatar.context, R.drawable.avatar01))
                    startAnimation(opponentAvatar, 1)
                    startAnimation(opponentUserName, 2)
                    startAnimation(opponentPoints, 3)
                }
            }
        }

        safelyCollectFlow(viewModel.currentUserData) {
            it?.let {
                binding.run {
                    hostUserName.text = it.userName
                    hostAvatar.setImageDrawable(ContextCompat.getDrawable(opponentAvatar.context, R.drawable.avatar02))
                    startAnimation(hostAvatar, 1)
                    startAnimation(hostUserName, 2)
                    startAnimation(hostPoints, 3)
                }
            }
        }

        safelyCollectFlow(viewModel.currentUserPoints) {
            binding.hostPoints.text = it.toString()
        }

        safelyCollectFlow(viewModel.opponentPoints) {
            binding.opponentPoints.text = it.toString()
        }

        safelyCollectFlow(viewModel.didYouWon) {
            if(it) {
                binding.lottieAnim.playAnimation()
            }
            binding.hostLabel.setText(if(it) R.string.you_won else R.string.you_lost)
            binding.opponentLabel.setText(if(it) R.string.you_won else R.string.you_lost)

        }
    }

    private fun startAnimation(view: View, delay: Long) {
        val slideDown = AnimationUtils.loadAnimation(binding.opponentAvatar.context, R.anim.slide_down)
        slideDown.startOffset = delay * 500
        view.startAnimation(slideDown)
    }
}