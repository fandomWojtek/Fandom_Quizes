package com.fandom.fandom.quiz.utils

import android.view.ViewGroup
import androidx.core.view.*
import androidx.fragment.app.Fragment

class MoveInsetsHandler(private val statusBarHeightUtil: StatusBarHeightUtil) {
    fun handleInsetsToMoveUnderStatusBar(fragment: Fragment) {
        val window = fragment.requireActivity().window
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(fragment.requireView()) { _, windowInsets ->
            applyInsets(fragment,windowInsets, setOf(WindowInsetsCompat.Type.navigationBars()))
        }
    }

    private fun applyInsets(fragment: Fragment, startWindowsInsets: WindowInsetsCompat, currentInsetTypes: Set<Int>): WindowInsetsCompat {
        val currentInsetTypeMask = currentInsetTypes.fold(0) { accumulator, type ->
            accumulator or type
        }
        val insets = startWindowsInsets.getInsets(currentInsetTypeMask)
        fragment.requireView().updateLayoutParams<ViewGroup.MarginLayoutParams> {
            updateMargins(insets.left, insets.top, insets.right, insets.bottom)
        }
        return WindowInsetsCompat.Builder()
            .setInsets(currentInsetTypeMask, insets)
            .build()
    }
    fun handleInsetsToMoveToNormalPosition(fragment: Fragment) {
        val window = fragment.requireActivity().window
        WindowCompat.setDecorFitsSystemWindows(window, true)
        ViewCompat.setOnApplyWindowInsetsListener(fragment.requireView()) { _, windowInsets ->
            applyInsets(fragment,windowInsets, setOf(WindowInsetsCompat.Type.systemBars()))
        }
    }
}

