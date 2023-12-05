package com.fandom.fandom.quiz.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.AuthScreenFragmentBinding
import com.fandom.fandom.quiz.utils.viewBinding

class AuthFragment : Fragment(R.layout.auth_screen_fragment) {

    val binding by viewBinding(AuthScreenFragmentBinding::bind)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }



}