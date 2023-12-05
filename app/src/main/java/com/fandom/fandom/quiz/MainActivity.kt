package com.fandom.fandom.quiz

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.fandom.fandom.quiz.databinding.MainActivityBinding
import com.fandom.fandom.quiz.auth.AuthViewModel
import com.fandom.fandom.quiz.fcm.getToken
import com.fandom.fandom.quiz.utils.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    val binding by viewBinding(MainActivityBinding::inflate)

    private val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (!navController.navigateUp()) {
                isEnabled = false
                finish()
            }
        }
    }
    private val navController: NavController
        get() = Navigation.findNavController(binding.quizNavHost)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, callback)

        if (supportFragmentManager.findFragmentByTag(NavHostFragment::class.java.name) == null) {
            val finalHost = NavHostFragment.create(R.navigation.quiz_navigation)
            supportFragmentManager.beginTransaction()
                .replace(R.id.quizNavHost, finalHost, NavHostFragment::class.java.name)
                .setPrimaryNavigationFragment(finalHost)
                .commit()
        }

    }
}