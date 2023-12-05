package com.fandom.fandom.quiz.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.fandom.fandom.quiz.remoteDb.UpdateCurrentUserLastInteractionTimeUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class UpdateUserClass(val fragment: Fragment) : KoinComponent {
    private val useCase: UpdateCurrentUserLastInteractionTimeUseCase by inject()
    private var job: Job? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                job = fragment.lifecycleScope.launch {
                    useCase.updateCurrentUserLastInteractionTime()
                }
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onStop(owner: LifecycleOwner) {
                            job?.cancel()
                        }
                    })
                }
            }
        })
    }

}