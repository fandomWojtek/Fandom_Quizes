package com.fandom.fandom.quiz.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

const val CURRENT_POSITION = "position"

class QuestionsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        val fragment = QuestionFragment()
        fragment.arguments = Bundle().apply {
            putInt(CURRENT_POSITION, position)
        }
        return fragment
    }
}