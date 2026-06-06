package xyz.malefic.guptarealty.model

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class Webinar(
    val header: String,
    val title: String,
    val description: String,
    val date: Instant,
    val imageUrl: String,
)
