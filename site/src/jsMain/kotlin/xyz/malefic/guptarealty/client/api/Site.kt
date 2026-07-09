package xyz.malefic.guptarealty.client.api

import xyz.malefic.guptarealty.client.util.getApi
import xyz.malefic.guptarealty.client.util.postApi
import xyz.malefic.guptarealty.model.SiteInfo

suspend fun getSiteSettings() = getApi<SiteInfo>("site")

suspend fun postSiteSettings(
    settings: SiteInfo,
    token: String,
) = postApi("site", settings, token)
