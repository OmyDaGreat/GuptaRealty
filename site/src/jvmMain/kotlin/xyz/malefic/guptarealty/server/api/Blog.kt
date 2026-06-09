package xyz.malefic.guptarealty.server.api

import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.path
import xyz.malefic.guptarealty.model.BlogPost
import xyz.malefic.guptarealty.model.error
import xyz.malefic.guptarealty.model.json
import xyz.malefic.guptarealty.server.data.blogs
import xyz.malefic.guptarealty.server.util.json
import kotlin.uuid.Uuid

val blog: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/blog/post/{id}" bind GET to request@{ request ->
            val id =
                try {
                    Uuid.parse(request.path("id") ?: return@request Response(BAD_REQUEST).body("Missing blog post ID"))
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).json("Invalid blog post ID".error)
                }
            Response(OK).json(blogs.first { it.id == id })
        },
        "/api/blog/post" bind POST to request@{ request ->
            val post =
                try {
                    json.decodeFromString<BlogPost>(request.bodyString())
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).json("Invalid blog post".error)
                }
            blogs += post
            Response(OK)
        },
        // TODO: Search feature, new blog creation, etc.
    )
