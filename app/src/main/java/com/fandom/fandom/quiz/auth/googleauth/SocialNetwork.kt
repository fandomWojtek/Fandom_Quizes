package com.fandom.fandom.quiz.auth.googleauth

enum class SocialNetwork(val network: String, val endpointName: String, val parameterName: String, val screenMedium: ScreenMedium) {
    GOOGLE("google", "google/","id_token", ScreenMedium.GOOGLE),
}

