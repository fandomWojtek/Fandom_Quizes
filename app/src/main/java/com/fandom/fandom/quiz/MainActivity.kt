package com.fandom.fandom.quiz

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.fandom.fandom.quiz.databinding.MainActivityBinding
import com.fandom.fandom.quiz.utils.viewBinding

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    private val binding by viewBinding(MainActivityBinding::inflate)


    private val navController: NavController
        get() = Navigation.findNavController(binding.quizNavHost)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportFragmentManager.findFragmentByTag(NavHostFragment::class.java.name) == null) {
            val finalHost = NavHostFragment.create(R.navigation.quiz_navigation)
            supportFragmentManager.beginTransaction()
                .replace(R.id.quizNavHost, finalHost, NavHostFragment::class.java.name)
                .setPrimaryNavigationFragment(finalHost)
                .commit()
        }

    }
}