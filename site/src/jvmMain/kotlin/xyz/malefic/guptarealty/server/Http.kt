package xyz.malefic.guptarealty.server

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import xyz.malefic.guptarealty.server.api.blog
import xyz.malefic.guptarealty.server.api.webinar
import xyz.malefic.guptarealty.server.util.mimeTypes
import xyz.malefic.guptarealty.server.util.staticRoots
import java.nio.file.Files

private fun serveStaticFile(req: Request): Response {
    val requestPath = req.uri.path.removePrefix("/")
    val ext = requestPath.substringAfterLast('.', "")
    val target = if (requestPath.isEmpty() || ext.isEmpty()) "index.html" else requestPath
    val contentType = mimeTypes.getOrDefault(ext.lowercase(), "text/html; charset=utf-8")

    for (root in staticRoots) {
        val file = root.resolve(target).normalize()
        if (!file.startsWith(root)) return Response(NOT_FOUND)
        if (Files.isRegularFile(file)) {
            val bytes = Files.readAllBytes(file)
            return Response(OK)
                .header("Content-Type", contentType)
                .body(bytes.inputStream(), bytes.size.toLong())
        }
    }

    return Response(NOT_FOUND)
}

val apiRoutes: RoutingHttpHandler =
    routes(
        "/api/ping" bind GET to { Response(OK).body("pong") },
        "/api/health" bind GET to { Response(OK).body("healthy") },
        *webinar,
        *blog,
    )

val http: HttpHandler =
    object : HttpHandler {
        override fun invoke(req: Request): Response =
            try {
                val response = apiRoutes(req)
                if (response.status == NOT_FOUND) serveStaticFile(req) else response
            } catch (_: Exception) {
                serveStaticFile(req)
            }
    }
