package xyz.malefic.guptarealty.client.util.js

@JsModule("@js-joda/timezone")
@JsNonModule
external object JsJodaTimeZoneModule

@OptIn(ExperimentalJsExport::class)
@JsExport
val jsJodaTz = JsJodaTimeZoneModule
