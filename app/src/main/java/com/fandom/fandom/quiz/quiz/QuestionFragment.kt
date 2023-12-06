package com.fandom.fandom.quiz.quiz

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
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

    private val viewModel: QuizViewModel by lazy {(requireParentFragment() as QuizFragment).quizViewModel}
    val binding by viewBinding(QuestionFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getQuiz()
        safelyCollectFlow(viewModel.quiz) {
            val quizQuestion = it?.questions?.get(position)
            binding.run {
                question.text = quizQuestion?.text
                quizQuestion?.answers?.run {
                    answer1.text = get(0).text
                    answer1.setOnClickListener { onClick(answer1, get(0)) }
                    answer2.text = get(1).text
                    answer2.setOnClickListener { onClick(answer2, get(1)) }
                    answer3.text = get(2).text
                    answer3.setOnClickListener { onClick(answer3, get(2))  }
                    answer4.text = get(3).text
                    answer4.setOnClickListener { onClick(answer4, get(3))  }
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
        if(answer.isCorrect) {
            view.setBackgroundColor(resources.getColor(R.color.correct_answer))
        } else {
            view.setBackgroundColor(resources.getColor(R.color.wrong_answer))
        }
    }

}