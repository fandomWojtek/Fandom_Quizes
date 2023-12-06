package com.fandom.fandom.quiz.communication

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val communicationModule= module {
    CommunicationManager(CoroutineScope(Dispatchers.Default))
}