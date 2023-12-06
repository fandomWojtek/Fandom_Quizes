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

    private val viewModel: QuizViewModel by viewModel()
    val binding by viewBinding(QuestionFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getQuiz()
        safelyCollectFlow(viewModel.quiz) {
            val question = it?.questions?.get(position)
            binding.question.text = question?.text
            binding.answer1.text = question?.answers?.get(0)?.text
            binding.answer2.text = question?.answers?.get(1)?.text
            binding.answer3.text = question?.answers?.get(2)?.text
            binding.answer4.text = question?.answers?.get(3)?.text
        }
    }
}