package xyz.malefic.guptarealty.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeInfo(
    val hero: HeroHomeInfo,
    val stats: Triple<String, String, String>,
    val statsNotice: String?,
    val help: HelpHomeInfo,
    val about: AboutHomeInfo,
    val insta: SocialHomeInfo,
    val youtube: SocialHomeInfo,
    val testimonial: Testimonial,
)

@Serializable
data class HeroHomeInfo(
    val title: String,
    val subtitle: String,
    val image: String,
)

@Serializable
data class HelpHomeInfo(
    val title: String,
    val description: String,
    val boxes: Pair<HelpBoxHomeInfo, HelpBoxHomeInfo>,
)

@Serializable
data class HelpBoxHomeInfo(
    val image: String,
    val title: String,
    val description: String,
)

@Serializable
data class SocialHomeInfo(
    val title: String,
    val description: String,
    val followLink: String,
    val posts: Pair<String, String>,
)

@Serializable
data class AboutHomeInfo(
    val title: String,
    val description: String,
    val image: String,
)
