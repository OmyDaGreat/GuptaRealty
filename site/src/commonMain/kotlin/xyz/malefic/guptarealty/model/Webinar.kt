package xyz.malefic.guptarealty.model

import kotlinx.serialization.Serializable

@Serializable
data class Webinar(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String,
    val videoUrl: String = "",
    val isLive: Boolean = false
)
