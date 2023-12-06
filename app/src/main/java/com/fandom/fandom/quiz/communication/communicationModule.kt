package com.fandom.fandom.quiz.communication

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val communicationModule= module {
    single {  CommunicationManager(CoroutineScope(Dispatchers.Default))}
}