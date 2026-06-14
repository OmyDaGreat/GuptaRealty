package xyz.malefic.guptarealty.api

import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.files.File
import org.w3c.xhr.FormData
import xyz.malefic.guptarealty.model.HomeInfo
import xyz.malefic.guptarealty.util.getApi
import xyz.malefic.guptarealty.util.getApiList
import xyz.malefic.guptarealty.util.postApi

suspend fun getHomeSettings() = getApi<HomeInfo>("home")

suspend fun postHomeSettings(
    settings: HomeInfo,
    token: String,
) = postApi("home", settings, token)

suspend fun getAssetList(token: String) = getApiList<String>("assets", token)

suspend fun uploadAsset(
    file: File,
    token: String,
) {
    val formData = FormData()
    formData.append("file", file)

    window
        .fetch("/api/assets", js("{ method: 'POST', body: formData, headers: { 'Authorization': 'Bearer ' + token } }"))
        .await()
}
