package xyz.malefic.guptarealty.model

import kotlinx.serialization.Serializable

@Serializable
data class SiteInfo(
    val siteName: String,
    val agentName: String,
    val agentLicense: String,
    val agentPhone: String,
    val agentEmail: String,
    val agentAddress: String,
    val brokerageName: String,
    val footerDescription: String,
    val logoUrl: String,
)
