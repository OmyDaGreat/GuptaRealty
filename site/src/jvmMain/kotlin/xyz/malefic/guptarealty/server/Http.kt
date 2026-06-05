package xyz.malefic.guptarealty.server

import kotlinx.serialization.json.Json
import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.AllowAllOriginPolicy
import org.http4k.filter.CorsPolicy
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import xyz.malefic.guptarealty.model.Message
import java.nio.file.Files
import java.nio.file.Paths

val corsPolicy =
    CorsPolicy(
        headers = listOf("Content-Type"),
        methods = Method.entries,
        originPolicy = AllowAllOriginPolicy,
    )

private val json = Json { ignoreUnknownKeys = true }

private val mimeTypes =
    mapOf(
        "html" to "text/html; charset=utf-8",
        "js" to "application/javascript",
        "css" to "text/css",
        "jpg" to "image/jpeg",
        "jpeg" to "image/jpeg",
        "png" to "image/png",
        "svg" to "image/svg+xml",
        "ico" to "image/x-icon",
        "webp" to "image/webp",
        "woff" to "font/woff",
        "woff2" to "font/woff2",
        "json" to "application/json",
    )

private fun serveStaticFile(req: Request): Response {
    val requestPath = req.uri.path.removePrefix("/")
    val staticDirs =
        listOf(
            Paths.get("build", "dist", "js", "productionExecutable"),
            Paths.get("build", "dist", "js", "productionExecutable", "public"),
            Paths.get("/app", "site", "build", "dist", "js", "productionExecutable"),
            Paths.get("/app", "site", "build", "dist", "js", "productionExecutable", "public"),
        )
    val fileName = requestPath.ifEmpty { "index.html" }
    val ext = fileName.substringAfterLast('.', "").lowercase()
    val contentType = mimeTypes[ext] ?: "application/octet-stream"

    for (baseDir in staticDirs) {
        val filePath = baseDir.resolve(fileName).normalize()
        if (Files.exists(filePath) && Files.isRegularFile(filePath)) {
            return try {
                val bytes = Files.readAllBytes(filePath)
                Response(OK)
                    .header("Content-Type", contentType)
                    .body(bytes.inputStream(), bytes.size.toLong())
            } catch (e: Exception) {
                Response(NOT_FOUND).body(e.toString())
            }
        }
    }
    return Response(NOT_FOUND)
}

val apiRoutes: RoutingHttpHandler =
    routes(
        "/api/ping" bind GET to { Response(OK).body("pong") },
        "/api/health" bind GET to { Response(OK).body("healthy") },
        "/api/messages" bind GET to {
            val messages =
                listOf(
                    Message(1, "Hello from the server!", System.currentTimeMillis()),
                    Message(2, "This data is shared via commonMain!", System.currentTimeMillis() - 5000),
                    Message(3, "Fetched via JSON API", System.currentTimeMillis() - 10000),
                )
            Response(OK)
                .header("Content-Type", APPLICATION_JSON.value)
                .body(json.encodeToString(messages))
        },
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
