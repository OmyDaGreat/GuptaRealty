package xyz.malefic.guptarealty.server.api

import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import xyz.malefic.guptarealty.model.json
import xyz.malefic.guptarealty.server.data.description
import xyz.malefic.guptarealty.server.util.auth
import xyz.malefic.guptarealty.server.util.error
import xyz.malefic.guptarealty.server.util.json

val home: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/home/description" bind GET to { request ->
            json(description)
        },
        "/api/home/description" bind POST to
            auth {
                description =
                    try {
                        json.decodeFromString<String>(bodyString())
                    } catch (e: Exception) {
                        return@auth error("Invalid blog post")
                    }

                Response(OK)
            },
    )
