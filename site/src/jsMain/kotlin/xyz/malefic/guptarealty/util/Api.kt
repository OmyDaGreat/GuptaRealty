package xyz.malefic.guptarealty.util

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import xyz.malefic.guptarealty.api.json

suspend inline fun <reified T> api(url: String) =
    window.api
        .getBytes(url)
        .decodeToString()
        .let { json.decodeFromString<T>(it) }
