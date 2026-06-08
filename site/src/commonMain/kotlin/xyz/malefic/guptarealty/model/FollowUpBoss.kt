package xyz.malefic.guptarealty.model

import kotlinx.serialization.Serializable

@Serializable
data class EmailEntry(
    val value: String,
)

@Serializable
data class PhoneEntry(
    val value: String,
)

@Serializable
data class EventPerson(
    val firstName: String,
    val lastName: String,
    val emails: List<EmailEntry>,
    val phones: List<PhoneEntry>,
    val contacted: Boolean = false,
    val source: String,
    val tags: List<String>,
)

@Serializable
data class FollowUpBossEvent(
    val source: String = "guptarealty.com/webinar",
    val system: String = "GuptaRealty",
    val type: String = "Registration",
    val person: EventPerson,
    val description: String,
)
