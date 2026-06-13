package xyz.malefic.guptarealty.api

import xyz.malefic.guptarealty.util.getApi
import xyz.malefic.guptarealty.util.postApi

suspend fun getDescription() = getApi<String>("home/description")

suspend fun postDescription(
    description: String,
    token: String,
) = postApi("webinar", description, token)
