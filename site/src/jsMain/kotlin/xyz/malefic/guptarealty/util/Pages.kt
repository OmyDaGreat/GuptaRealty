package xyz.malefic.guptarealty.util

enum class Pages(
    val value: String,
    val route: String,
) {
    INDEX("Home", "/"),
    WEBINAR("Webinar", "/webinar"),
    BLOG("Blog", "/blog"),
    TESTIMONIALS("Testimonials", "/testimonials"),
    CONTACT("Contact", "/contact"),
}
