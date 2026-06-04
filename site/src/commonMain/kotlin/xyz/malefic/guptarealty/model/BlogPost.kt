package xyz.malefic.guptarealty.model

import kotlinx.serialization.Serializable

@Serializable
data class BlogPost(
    val id: String,
    val title: String,
    val summary: String,
    val imageUrl: String,
    val category: String,
    val date: String
)
