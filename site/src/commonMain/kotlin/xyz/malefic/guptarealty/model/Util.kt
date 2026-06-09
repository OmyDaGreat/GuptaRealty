package xyz.malefic.guptarealty.model

import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true }

val String.error
    get() = Error(this)
