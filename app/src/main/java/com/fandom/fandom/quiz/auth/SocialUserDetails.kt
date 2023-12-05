package com.fandom.fandom.quiz.auth

data class SocialUserDetails(val token: String, val name: String, val email: String, private val type: String) {
    fun toSocialRequest(): Pair<SocialLoginRequest,SocialNetwork> = SocialLoginRequest(token,name,email) to socialNetwork

    constructor(token: String, email: String, name: String, socialNetwork: SocialNetwork) : this(token, name, email, socialNetwork.name)

    val socialNetwork: SocialNetwork get() = SocialNetwork.valueOf(type)
}
