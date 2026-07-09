package xyz.malefic.guptarealty.server.api

import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import xyz.malefic.guptarealty.model.SiteInfo
import xyz.malefic.guptarealty.model.json
import xyz.malefic.guptarealty.server.data.siteInfo
import xyz.malefic.guptarealty.server.util.auth
import xyz.malefic.guptarealty.server.util.error
import xyz.malefic.guptarealty.server.util.json

val site: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/site" bind GET to { _ ->
            json(siteInfo)
        },
        "/api/site" bind POST to
            auth {
                siteInfo =
                    try {
                        json.decodeFromString<SiteInfo>(bodyString())
                    } catch (_: Exception) {
                        return@auth error("Invalid site settings")
                    }

                Response(OK)
            },
    )
