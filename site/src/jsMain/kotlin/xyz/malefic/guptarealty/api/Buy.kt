package xyz.malefic.guptarealty.api

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import xyz.malefic.guptarealty.model.Property

suspend fun getProperties() = window.api.getBytes("buy/properties").decodeToString().let { json.decodeFromString<List<Property>>(it) }
