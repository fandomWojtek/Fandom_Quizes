package com.fandom.fandom.quiz.auth.googleauth

data class SocialLoginRequest(val token: String, val name: String, val email: String)