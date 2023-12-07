package com.fandom.fandom.quiz.quiz

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.addPauseListener
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.FragmentQuizBinding
import com.fandom.fandom.quiz.quiz.presentation.AwaitOpponentResponseViewModel
import com.fandom.fandom.quiz.quiz.presentation.OpponentResponses
import com.fandom.fandom.quiz.quiz.presentation.QuizViewModel
import com.fandom.fandom.quiz.utils.safelyCollectFlow
import com.fandom.fandom.quiz.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private lateinit var questionsAdapter: QuestionsAdapter
    private lateinit var viewPager: ViewPager2

    private val awaitOpponentResponseViewModel: AwaitOpponentResponseViewModel by viewModel()
    internal val quizViewModel: QuizViewModel by viewModel()
    val binding by viewBinding(FragmentQuizBinding::bind)
    val DURATION: Long = 1000*60
    lateinit var animator1: ObjectAnimator
    lateinit var animator2: ObjectAnimator
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adjustAnimators()


        questionsAdapter = QuestionsAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = questionsAdapter
        viewPager.isUserInputEnabled = false
        safelyCollectFlow(awaitOpponentResponseViewModel.opponentResponseState) {
            playWithOpponentResponses(it)
        }

        safelyCollectFlow(quizViewModel.goToNextQuestion) {
            viewPager.currentItem = it
        }

        safelyCollectFlow(quizViewModel.finishFlowOfQuiz) {
            findNavController().navigate(R.id.action_uizScreenNav_to_summaryFragmentNav)
        }

        safelyCollectFlow(awaitOpponentResponseViewModel.quizMetaData) {
            binding.progressOfTheOpponent.weightSum = it.questionNumber.toFloat()
        }
        safelyCollectFlow(quizViewModel.waitingForYourOpponentToFinish) {
            binding.watingForOpponent.isVisible = true
        }
    }

    private fun adjustAnimators() {
        animator1 = ObjectAnimator.ofInt(binding.flameScroll, "scrollX", 0, binding.flameScroll[0].width - binding.flameScroll.width).apply {
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = ObjectAnimator.INFINITE
            duration = DURATION
            start()
        }
        animator2 = ObjectAnimator.ofInt(binding.secondFlameScroll, "scrollX", 0, binding.secondFlameScroll[0].width - binding.secondFlameScroll.width).apply {
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = ObjectAnimator.INFINITE
            duration = DURATION*2
            start()
        }
        animator1.addUpdateListener {
            android.util.Log.e("TAG", "adjustAnimators: $it" )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        animator1.cancel()
        animator2.cancel()
    }

    private fun playWithOpponentResponses(it: OpponentResponses) {
        binding.progressOfTheOpponent.removeAllViews()
        it.list.indices.map { index ->
            val createdView = View(requireContext())
            val drawable = when (index) {
                0 -> R.drawable.left_progress
                it.list.size - 1 -> R.drawable.rigth_progress
                else -> R.drawable.progress
            }

            createdView.background = DrawableCompat.wrap(AppCompatResources.getDrawable(requireContext(), drawable)!!)
                .mutate()
                .also { draw ->
                    DrawableCompat.setTint(
                        draw,
                        ContextCompat.getColor(requireContext(), if (it.list[index].correct) R.color.correct_answer else R.color.wrong_answer)
                    )
                }
            createdView.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
            binding.progressOfTheOpponent.addView(createdView)
            val positionOfFlame = (index + 1).toFloat() / (try {
                awaitOpponentResponseViewModel.quizMetaData.replayCache.last().questionNumber.toFloat()
            } catch (ex: Exception) {
                5f
            })*0.8f
            binding.flameScroll.updateLayoutParams<ConstraintLayout.LayoutParams> { matchConstraintPercentWidth = positionOfFlame }
            binding.secondFlameScroll.updateLayoutParams<ConstraintLayout.LayoutParams> { matchConstraintPercentWidth = positionOfFlame }
            animator1.cancel()
            animator2.cancel()
            adjustAnimators()
        }
    }
}
