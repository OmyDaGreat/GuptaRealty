package xyz.malefic.guptarealty.server.api

import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Method.PUT
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.path
import xyz.malefic.guptarealty.model.BlogPostRequest
import xyz.malefic.guptarealty.model.error
import xyz.malefic.guptarealty.model.json
import xyz.malefic.guptarealty.server.data.blogs
import xyz.malefic.guptarealty.server.util.json
import kotlin.uuid.Uuid

val blog: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/blog/posts" bind GET to {
            Response(OK).json(blogs)
        },
        "/api/blog/post/{id}" bind GET to request@{ request ->
            val id =
                try {
                    Uuid.parse(request.path("id") ?: return@request Response(BAD_REQUEST).json("Missing blog post ID".error))
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).json("Invalid blog post ID".error)
                }

            Response(OK).json(blogs.first { it.id == id })
        },
        "/api/blog/post" bind POST to request@{ request ->
            val post =
                try {
                    json.decodeFromString<BlogPostRequest>(request.bodyString())
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).json("Invalid blog post".error)
                }

            blogs += post.toResponse(Uuid.random())
            Response(OK)
        },
        "/api/blog/post/{id}" bind PUT to request@{ request ->
            val id = request.path("id")?.let { Uuid.parse(it) } ?: return@request Response(BAD_REQUEST).json("Missing blog post ID".error)
            val post =
                try {
                    json.decodeFromString<BlogPostRequest>(request.bodyString()).toResponse(id)
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).json("Invalid blog post".error)
                }

            val index = blogs.indexOfFirst { it.id == id }
            if (index == -1) return@request Response(BAD_REQUEST).json("Blog post not found".error)
            blogs[index] = post
            Response(OK)
        },
        "/api/blog/post/{id}" bind DELETE to request@{ request ->
            val id = request.path("id")?.let { Uuid.parse(it) } ?: return@request Response(BAD_REQUEST).json("Missing blog post ID".error)
            val index = blogs.indexOfFirst { it.id == id }
            if (index == -1) return@request Response(BAD_REQUEST).json("Blog post not found".error)
            blogs.removeAt(index)
            Response(OK)
        },
        // TODO: Search feature, new blog creation, etc.
    )
