package xyz.malefic.guptarealty.server.util

import org.http4k.core.ContentType
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.contentType
import xyz.malefic.guptarealty.model.json

inline fun <reified T> Response.json(obj: T) = body(json.encodeToString(obj))

inline fun <reified T> json(obj: T) = Response(OK).contentType(ContentType.APPLICATION_JSON).json<T>(obj)

fun error(error: String) = Response(Status.BAD_REQUEST).contentType(ContentType.APPLICATION_JSON).json(error)
