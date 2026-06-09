package xyz.malefic.guptarealty.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class BlogPost(
    val id: Uuid,
    val title: String,
    val summary: String,
    val imageUrl: String,
    val tags: List<String>,
    val date: LocalDate,
)
