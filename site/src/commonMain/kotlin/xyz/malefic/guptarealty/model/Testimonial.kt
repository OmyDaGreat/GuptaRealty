package xyz.malefic.guptarealty.model

import kotlinx.serialization.Serializable

@Serializable
data class Testimonial(
    val author: String,
    val location: String,
    val quote: String,
    val rating: Int = 5,
    val avatarUrl: String = ""
)
