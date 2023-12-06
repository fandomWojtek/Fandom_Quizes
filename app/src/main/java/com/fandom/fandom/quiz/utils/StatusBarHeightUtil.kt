package com.fandom.fandom.quiz.utils

import android.app.Activity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class StatusBarHeightUtil {
    var statusBarHeight: Int = 0
    fun setUp(context: Activity) {
        ViewCompat.setOnApplyWindowInsetsListener(context.window.decorView.rootView) { _, windowInsets ->
            statusBarHeight = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            windowInsets
        }
    }

    fun setUp(fragment: Fragment) {
        ViewCompat.setOnApplyWindowInsetsListener(fragment.requireView()) { _, windowInsets ->
            statusBarHeight = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            windowInsets
        }
    }
}

