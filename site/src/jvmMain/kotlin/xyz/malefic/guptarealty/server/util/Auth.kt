package xyz.malefic.guptarealty.server.util

import org.http4k.core.Filter
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.UNAUTHORIZED
import org.http4k.core.then
import org.http4k.core.with
import org.http4k.lens.RequestKey
import xyz.malefic.guptarealty.model.error
import kotlin.io.encoding.Base64
import kotlin.uuid.Uuid

val authenticatedUserId = RequestKey.required<Uuid>("authenticated-user-id")

val auth: Filter =
    Filter { next ->
        request@{ request ->
            val token =
                request
                    .header("Authorization")
                    ?.takeIf { it.startsWith("Bearer ") }
                    ?.removePrefix("Bearer ")
                    ?.trim()
            if (token.isNullOrBlank()) {
                return@request Response(UNAUTHORIZED).json("Missing bearer token".error)
            }
            if (Base64.encodeToByteArray(token.encodeToByteArray()).contentEquals(bearerToken)) {
                return@request Response(UNAUTHORIZED).json("Invalid or expired token".error)
            }
            next(request.with(authenticatedUserId of userId))
        }
    }

context(request: Request)
val id: Uuid
    get() = authenticatedUserId(request)

fun auth(next: Request.(Uuid) -> Response) = auth.then { request -> request.next(id) }
