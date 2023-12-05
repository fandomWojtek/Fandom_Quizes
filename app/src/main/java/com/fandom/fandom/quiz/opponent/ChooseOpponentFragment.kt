package com.fandom.fandom.quiz.opponent

import androidx.fragment.app.Fragment
import com.fandom.fandom.quiz.R

class ChooseOpponentFragment:Fragment(R.layout.fragment_choose_oponent) {
    val categoryId by lazy {
        requireArguments().getString("categoryId")
    }
}