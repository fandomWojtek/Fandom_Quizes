package com.fandom.fandom.quiz.quiz

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.QuestionFragmentBinding
import com.fandom.fandom.quiz.quiz.presentation.QuizViewModel
import com.fandom.fandom.quiz.utils.safelyCollectFlow
import com.fandom.fandom.quiz.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

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
                    answer1.setOnClickListener { viewModel.handleAnswer(get(0).isCorrect, position) }
                    answer2.text = get(1).text
                    answer2.setOnClickListener { viewModel.handleAnswer(get(1).isCorrect, position) }
                    answer3.text = get(2).text
                    answer3.setOnClickListener { viewModel.handleAnswer(get(2).isCorrect, position) }
                    answer4.text = get(3).text
                    answer4.setOnClickListener { viewModel.handleAnswer(get(3).isCorrect, position) }

                }
            }
            viewModel.startTimer()
        }
    }

}