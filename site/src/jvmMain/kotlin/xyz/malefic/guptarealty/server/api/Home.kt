package xyz.malefic.guptarealty.server.api

import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import xyz.malefic.guptarealty.model.HomeInfo
import xyz.malefic.guptarealty.model.json
import xyz.malefic.guptarealty.server.data.homeInfo
import xyz.malefic.guptarealty.server.util.auth
import xyz.malefic.guptarealty.server.util.error
import xyz.malefic.guptarealty.server.util.json

val home: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/home" bind GET to { request ->
            json(homeInfo)
        },
        "/api/home" bind POST to
            auth {
                homeInfo =
                    try {
                        json.decodeFromString<HomeInfo>(bodyString())
                    } catch (e: Exception) {
                        return@auth error("Invalid home settings")
                    }

                Response(OK)
            },
    )
