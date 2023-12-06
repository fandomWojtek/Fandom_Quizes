package com.fandom.fandom.quiz.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.QuestionFragmentBinding
import com.fandom.fandom.quiz.quiz.presentation.QuizViewModel
import com.fandom.fandom.quiz.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizFragment: Fragment(R.layout.fragment_quiz) {

    private lateinit var questionsAdapter: QuestionsAdapter
    private lateinit var viewPager: ViewPager2
    val binding by viewBinding(QuestionFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        questionsAdapter = QuestionsAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = questionsAdapter
    }
}
