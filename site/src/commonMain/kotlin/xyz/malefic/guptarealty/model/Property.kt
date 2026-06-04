package xyz.malefic.guptarealty.model

import kotlinx.serialization.Serializable

@Serializable
data class Property(
    val id: String,
    val price: Double,
    val address: String,
    val beds: Int,
    val baths: Double,
    val sqft: Int,
    val imageUrl: String,
    val tags: List<String> = emptyList(),
    val isFeatured: Boolean = false,
    val description: String = "",
    val features: List<String> = emptyList()
)
