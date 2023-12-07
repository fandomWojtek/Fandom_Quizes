package com.fandom.fandom.quiz.quiz

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.QuestionFragmentBinding
import com.fandom.fandom.quiz.quiz.api.Answer
import com.fandom.fandom.quiz.quiz.presentation.QuizViewModel
import com.fandom.fandom.quiz.utils.safelyCollectFlow
import com.fandom.fandom.quiz.utils.viewBinding

class QuestionFragment : Fragment(R.layout.question_fragment) {

    private val position by lazy {
        requireArguments().getInt(CURRENT_POSITION)
    }

    private val viewModel: QuizViewModel by lazy { (requireParentFragment() as QuizFragment).quizViewModel }
    val binding by viewBinding(QuestionFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getQuiz()
        safelyCollectFlow(viewModel.quiz) {
            val quizQuestion = it?.questions?.get(position)
            binding.run {
                val buttons = listOf(answer1, answer2, answer3, answer4)
                buttons.forEach { buton -> buton.isGone = true }
                question.text = quizQuestion?.text
                val answers = quizQuestion?.answers ?: emptyList()
                questionCounter.text = getString(R.string.question_counter, position, it?.questions?.size)
                answers.indices.forEach { index ->
                    buttons[index].visibility = View.VISIBLE
                    buttons[index].text = answers[index].text
                    buttons[index].setOnClickListener { onClick(buttons[index], answers[index]) }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startTimer()
    }

    private fun onClick(view: View, answer: Answer) {
        viewModel.handleAnswer(answer.isCorrect, position)
        if (answer.isCorrect) {
            view.setBackgroundColor(resources.getColor(R.color.correct_answer))
        } else {
            view.setBackgroundColor(resources.getColor(R.color.wrong_answer))
        }
    }

}