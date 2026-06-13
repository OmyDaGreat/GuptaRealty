package xyz.malefic.guptarealty.util

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import xyz.malefic.guptarealty.model.json

suspend inline fun <reified T> getApi(
    url: String,
    token: String? = null,
) = window.api
    .getBytes(url, headers = token?.let { mapOf("Authorization" to "Bearer $it") } ?: emptyMap())
    .decodeToString()
    .let { json.decodeFromString<T>(it) }

suspend fun postApi(
    url: String,
    token: String? = null,
) = window.api.postBytes(url, headers = token?.let { mapOf("Authorization" to "Bearer $it") } ?: emptyMap())

suspend inline fun <reified T> postApi(
    url: String,
    body: T,
    token: String? = null,
) = window.api.postBytes(
    url,
    body = json.encodeToString(body).encodeToByteArray(),
    headers = token?.let { mapOf("Authorization" to "Bearer $it") } ?: emptyMap(),
)

suspend inline fun <reified T> putApi(
    url: String,
    body: T,
    token: String? = null,
) = window.api.putBytes(
    url,
    body = json.encodeToString(body).encodeToByteArray(),
    headers = token?.let { mapOf("Authorization" to "Bearer $it") } ?: emptyMap(),
)

suspend fun deleteApi(
    url: String,
    token: String? = null,
) = window.api.deleteBytes(url, headers = token?.let { mapOf("Authorization" to "Bearer $it") } ?: emptyMap())
