package xyz.malefic.guptarealty.server

import co.touchlab.kermit.Logger
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import xyz.malefic.guptarealty.server.api.admin
import xyz.malefic.guptarealty.server.api.assets
import xyz.malefic.guptarealty.server.api.blog
import xyz.malefic.guptarealty.server.api.contact
import xyz.malefic.guptarealty.server.api.home
import xyz.malefic.guptarealty.server.api.site
import xyz.malefic.guptarealty.server.api.testimonial
import xyz.malefic.guptarealty.server.api.webinar
import xyz.malefic.guptarealty.server.util.assetsPath
import xyz.malefic.guptarealty.server.util.mimeTypes
import xyz.malefic.guptarealty.server.util.staticRoots
import java.io.File
import java.nio.file.Files

private fun serveStaticFile(req: Request): Response {
    val requestPath = req.uri.path.removePrefix("/")

    if (requestPath.startsWith("assets/")) {
        val fileName = requestPath.removePrefix("assets/")
        val file = File(assetsPath, fileName)
        if (file.exists() && file.isFile) {
            val ext = fileName.substringAfterLast('.', "")
            val contentType = mimeTypes.getOrDefault(ext.lowercase(), "application/octet-stream")
            val bytes = file.readBytes()
            return Response(OK)
                .header("Content-Type", contentType)
                .body(bytes.inputStream(), bytes.size.toLong())
        }
        return Response(NOT_FOUND)
    }

    val ext = requestPath.substringAfterLast('.', "")
    val target = if (requestPath.isBlank() || ext.isBlank()) "index.html" else requestPath
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
        *admin,
        *assets,
        *blog,
        *contact,
        *home,
        *site,
        *testimonial,
        *webinar,
    )

val http: HttpHandler =
    { request ->
        if (request.uri.path.startsWith("/api/")) {
            apiRoutes(request).also { Logger.d { "Serving API: ${request.uri.path}" } }
        } else {
            serveStaticFile(request).also { Logger.d { "Serving static file: ${request.uri.path}" } }
        }
    }
