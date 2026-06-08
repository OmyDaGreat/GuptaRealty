package xyz.malefic.guptarealty.server

import co.touchlab.kermit.Logger
import org.http4k.core.then
import org.http4k.filter.ServerFilters
import org.http4k.server.Undertow
import org.http4k.server.asServer

fun main() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080
    val server =
        ServerFilters
            .Cors(corsPolicy)
            .then(http)
            .asServer(Undertow(port))
            .start()

    Logger.i { "Server started on port ${server.port()}!" }
}
