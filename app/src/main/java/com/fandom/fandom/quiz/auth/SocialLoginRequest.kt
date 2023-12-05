package com.fandom.fandom.quiz.auth

data class SocialLoginRequest(val token: String, val name: String, val email: String)