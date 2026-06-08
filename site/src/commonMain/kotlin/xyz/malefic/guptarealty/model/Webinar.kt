package xyz.malefic.guptarealty.model

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class Registration(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
)

@Serializable
data class Webinar(
    val header: String,
    val title: String,
    val description: String,
    val date: Instant,
    val imageUrl: String,
)

@Serializable
data class WebinarTip(
    val icon: String,
    val title: String,
    val description: String,
)

@Serializable
data class WebinarTipsSection(
    val header: String,
    val mistakes: List<WebinarTip>,
)

@Serializable
data class WebinarReview(
    val reviewer: String,
    val reviewerDescription: String,
    val reviewerImage: String,
    val review: String,
)
