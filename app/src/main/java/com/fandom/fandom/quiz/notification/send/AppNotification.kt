package com.fandom.fandom.quiz.notification.send


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppNotification(
    @SerialName("contents")
    val contents: Contents = Contents(""),
    @SerialName("custom_data")
    val customData: Communication,
    @SerialName("include_aliases")
    val includeAliases: IncludeAliases
)

@Serializable
data class Contents(
    @SerialName("en")
    val en: String
)

@Serializable
data class IncludeAliases(
    @SerialName("external_id")
    val externalId: List<String>
)