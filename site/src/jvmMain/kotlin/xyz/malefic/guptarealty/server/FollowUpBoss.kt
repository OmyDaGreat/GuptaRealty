package xyz.malefic.guptarealty.server

import org.http4k.client.OkHttp
import org.http4k.core.Body
import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.Credentials
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.lens.accept
import org.http4k.lens.basicAuthentication
import org.http4k.lens.contentType
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import xyz.malefic.guptarealty.model.EmailEntry
import xyz.malefic.guptarealty.model.EventPerson
import xyz.malefic.guptarealty.model.FUBPayload
import xyz.malefic.guptarealty.model.FollowUpBossEvent
import xyz.malefic.guptarealty.model.PhoneEntry
import xyz.malefic.guptarealty.model.json
import xyz.malefic.guptarealty.server.data.webinarName
import java.util.Base64

private val apiKey: String? =
    System.getProperty("FUB_API_KEY")
        ?: System.getenv("FUB_API_KEY")
private val client = OkHttp()
private val credentials: String by lazy { Base64.getEncoder().encodeToString("$apiKey:".toByteArray()) }

val fub: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/fub" bind POST to request@{ request ->
            if (apiKey == null) {
                return@request Response(BAD_REQUEST).body("Missing FUB_API_KEY environment variable")
            }

            val model = json.decodeFromString<FUBPayload>(request.bodyString())

            val payload =
                FollowUpBossEvent(
                    person =
                        EventPerson(
                            model.firstName,
                            model.lastName,
                            listOf(EmailEntry(model.email)),
                            listOf(PhoneEntry(model.phone)),
                            source = "guptarealty.com/webinar",
                            tags = listOf("Webinar"),
                        ),
                    description = "Signed up for webinar \"$webinarName\"",
                )

            val request =
                Request(POST, "https://api.followupboss.com/v1/events")
                    .accept(APPLICATION_JSON)
                    .basicAuthentication(Credentials(apiKey, ""))
                    .contentType(APPLICATION_JSON)
                    .body(Body(json.encodeToString(payload)))

            client(request)
        },
    )
