package xyz.malefic.guptarealty.server.api

import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.MultipartFormBody
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import xyz.malefic.guptarealty.server.util.assetsPath
import xyz.malefic.guptarealty.server.util.auth
import xyz.malefic.guptarealty.server.util.error
import xyz.malefic.guptarealty.server.util.json
import java.io.File
import java.nio.file.Files

private val assetsDir = File(assetsPath).apply { if (!exists()) mkdirs() }

val images: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/assets" bind GET to
            auth {
                json(assetsDir.listFiles()?.map { it.name } ?: emptyList())
            },
        "/api/assets" bind POST to
            auth {
                val body = MultipartFormBody.from(this)
                val file = body.file("file") ?: return@auth error("No file uploaded")

                val fileName = file.filename
                val targetFile = File(assetsDir, fileName)

                Files.write(targetFile.toPath(), file.content.readAllBytes())

                json("/assets/$fileName")
            },
    )
