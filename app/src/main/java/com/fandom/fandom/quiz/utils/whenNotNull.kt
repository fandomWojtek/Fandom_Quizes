package com.fandom.fandom.quiz.utils

inline fun <T : Any> whenNotNull(input: T?, callback: (T) -> Unit) {
    input?.also(callback)
}