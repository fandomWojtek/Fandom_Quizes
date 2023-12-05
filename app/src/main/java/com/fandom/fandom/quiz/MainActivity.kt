package com.fandom.fandom.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fandom.fandom.quiz.fcm.getToken
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.main_activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val token = getToken()
            println("token = $token")
        }
    }
}