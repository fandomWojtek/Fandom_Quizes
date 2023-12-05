package com.fandom.fandom.quiz.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Fragment.safelyCollectFlow(flow: Flow<T>, lifecycleState: Lifecycle.State = Lifecycle.State.STARTED, block: (T) -> Unit): Job {
    return viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
            flow.collect {
                block(it)
            }
        }
    }
}

fun <T> AppCompatActivity.safelyCollectFlow(flow: Flow<T>, lifecycleState: Lifecycle.State = Lifecycle.State.STARTED, block: (T) -> Unit): Job {
    return lifecycleScope.launch {
        repeatOnLifecycle(lifecycleState) {
            flow.collect {
                block(it)
            }
        }
    }
}