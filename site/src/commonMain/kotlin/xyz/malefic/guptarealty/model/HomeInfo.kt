package xyz.malefic.guptarealty.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeInfo(
    val heroTitle: String,
    val heroSubtitle: String,
    val heroImage: String,
    val aboutLabel: String,
    val aboutTitle: String,
    val aboutDescription: String,
    val aboutImage: String,
    val ctaTitle: String,
    val ctaDescription: String,
    val ctaSearchLink: String,
    val ctaDownloadLink: String,
)
