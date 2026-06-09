package xyz.malefic.guptarealty.server.util

import org.http4k.core.Response
import xyz.malefic.guptarealty.model.json

inline fun <reified T> Response.json(obj: T) = body(json.encodeToString(obj))
