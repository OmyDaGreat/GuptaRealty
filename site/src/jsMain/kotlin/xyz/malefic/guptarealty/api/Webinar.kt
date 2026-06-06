package xyz.malefic.guptarealty.api

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.datetime.TimeZone
import xyz.malefic.guptarealty.model.Webinar
import xyz.malefic.guptarealty.model.WebinarTipsSection

suspend fun getWebinar() =
    window.api
        .getBytes("webinar?tz=${TimeZone.currentSystemDefault().id}")
        .decodeToString()
        .let { json.decodeFromString<Webinar>(it) }

suspend fun getWebinarTips() =
    window.api
        .getBytes("webinar/tips")
        .decodeToString()
        .let { json.decodeFromString<WebinarTipsSection>(it) }
