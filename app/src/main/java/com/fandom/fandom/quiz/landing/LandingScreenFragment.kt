package com.fandom.fandom.quiz.landing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.LandingScreenFragmentBinding
import com.fandom.fandom.quiz.utils.viewBinding

class LandingScreenFragment: Fragment(R.layout.landing_screen_fragment) {

    val binding by viewBinding(LandingScreenFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}