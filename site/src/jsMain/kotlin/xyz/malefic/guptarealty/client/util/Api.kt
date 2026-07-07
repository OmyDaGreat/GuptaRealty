package xyz.malefic.guptarealty.client.util

import com.varabyte.kobweb.browser.api
import com.varabyte.kobweb.browser.http.bodyOf
import kotlinx.browser.window
import kotlinx.coroutines.await
import xyz.malefic.guptarealty.model.json

suspend inline fun <reified T> getApi(
    url: String,
    token: String? = null,
) = window.api
    .get(url, headers = token?.let { mapOf("Authorization" to "Bearer $it") } ?: emptyMap())
    .text()
    .await()
    .let { json.decodeFromString<T>(it) }

suspend inline fun <reified E> getApiList(
    url: String,
    token: String? = null,
) = window.api
    .get(url, headers = token?.let { mapOf("Authorization" to "Bearer $it") } ?: emptyMap())
    .text()
    .await()
    .let {
        try {
            json.decodeFromString<List<E>>(it)
        } catch (e: Exception) {
            console.log("Couldn't deserialize list: $e")
            emptyList()
        }
    }

suspend fun postApi(
    url: String,
    token: String? = null,
) = window.api.post(url, headers = token?.let { mapOf("Authorization" to "Bearer $it") } ?: emptyMap())

suspend inline fun <reified T> postApi(
    url: String,
    body: T,
    token: String? = null,
) = window.api
    .post(
        url,
        bodyOf(json.encodeToString(body), "application/json"),
        headers = token?.let { mapOf("Authorization" to "Bearer $it") } ?: emptyMap(),
    )

suspend inline fun <reified T> putApi(
    url: String,
    body: T,
    token: String? = null,
) = window.api
    .put(
        url,
        bodyOf(json.encodeToString(body), "application/json"),
        headers = token?.let { mapOf("Authorization" to "Bearer $it") } ?: emptyMap(),
    )

suspend fun deleteApi(
    url: String,
    token: String? = null,
) = window.api.delete(url, headers = token?.let { mapOf("Authorization" to "Bearer $it") } ?: emptyMap())
