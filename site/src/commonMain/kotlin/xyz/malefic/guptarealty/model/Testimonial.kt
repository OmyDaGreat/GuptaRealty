package xyz.malefic.guptarealty.model

import kotlinx.serialization.Serializable

@Serializable
data class Testimonial(
    val author: String,
    val quote: String,
    val imageSrc: String? = null,
)
