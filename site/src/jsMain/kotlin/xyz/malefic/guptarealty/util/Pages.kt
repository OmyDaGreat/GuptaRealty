package xyz.malefic.guptarealty.util

enum class Pages(
    val value: String,
    val route: String,
) {
    INDEX("Home", "/"),
    BUY("Buy", "/buy"),
    SELL("Sell", "/sell"),
    WEBINARS("Webinars", "/webinars"),
    BLOG("Blog", "/blog"),
    CONTACT("Contact", "/contact"),
}
