package com.fandom.fandom.quiz.quiz

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        questionsAdapter = QuestionsAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = questionsAdapter
        viewPager.isUserInputEnabled = false
        safelyCollectFlow(awaitOpponentResponseViewModel.opponentResponseState) {
            playWithOpponentResponses(it)
        }

        safelyCollectFlow(quizViewModel.goToNextQuestion) {
            if(it) {
                viewPager.currentItem = viewPager.currentItem + 1
            }
        }
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
        }
    }
}
