package xyz.malefic.guptarealty.util

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import xyz.malefic.guptarealty.model.json

suspend inline fun <reified T> getApi(url: String) =
    window.api
        .getBytes(url)
        .decodeToString()
        .let { json.decodeFromString<T>(it) }

suspend fun postApi(url: String) = window.api.postBytes(url)

suspend inline fun <reified T> postApi(
    url: String,
    body: T,
) = window.api.postBytes(url, body = json.encodeToString(body).encodeToByteArray())

suspend inline fun <reified T> putApi(
    url: String,
    body: T,
) = window.api.putBytes(url, body = json.encodeToString(body).encodeToByteArray())

suspend fun deleteApi(url: String) = window.api.deleteBytes(url)
