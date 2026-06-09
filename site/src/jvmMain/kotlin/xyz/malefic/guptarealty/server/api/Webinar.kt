package xyz.malefic.guptarealty.server.api

import co.touchlab.kermit.Logger
import org.http4k.client.OkHttp
import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.Credentials
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.accept
import org.http4k.lens.basicAuthentication
import org.http4k.lens.contentType
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import xyz.malefic.guptarealty.model.EmailEntry
import xyz.malefic.guptarealty.model.EventPerson
import xyz.malefic.guptarealty.model.FollowUpBossEvent
import xyz.malefic.guptarealty.model.PhoneEntry
import xyz.malefic.guptarealty.model.Registration
import xyz.malefic.guptarealty.model.error
import xyz.malefic.guptarealty.model.json
import xyz.malefic.guptarealty.server.data.currentWebinar
import xyz.malefic.guptarealty.server.data.registrations
import xyz.malefic.guptarealty.server.data.webinarName
import xyz.malefic.guptarealty.server.data.webinarReviews
import xyz.malefic.guptarealty.server.data.webinarTips
import xyz.malefic.guptarealty.server.util.json

private val log = Logger.withTag("Webinar")

private val fubApiKey: String? = System.getProperty("FUB_API_KEY") ?: System.getenv("FUB_API_KEY")
private val client = OkHttp()

val webinar: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/webinar" bind GET to { request ->
            Response(OK)
                .contentType(APPLICATION_JSON)
                .json(currentWebinar)
        },
        "/api/webinar" bind GET to request@{ request ->
            currentWebinar =
                try {
                    json.decodeFromString(request.bodyString())
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).json("Invalid webinar".error)
                }

            Response(OK)
        },
        "/api/webinar/tips" bind GET to {
            Response(OK)
                .contentType(APPLICATION_JSON)
                .json(webinarTips)
        },
        "/api/webinar/tips" bind POST to request@{ request ->
            webinarTips =
                try {
                    json.decodeFromString(request.bodyString())
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).json("Invalid webinar tips".error)
                }

            Response(OK)
        },
        "/api/webinar/reviews" bind GET to {
            Response(OK)
                .contentType(APPLICATION_JSON)
                .json(webinarReviews)
        },
        "/api/webinar/reviews" bind POST to request@{ request ->
            webinarReviews =
                try {
                    json.decodeFromString(request.bodyString())
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).json("Invalid webinar reviews".error)
                }

            Response(OK)
        },
        "/api/webinar/registrations" bind GET to request@{ request ->
            Response(OK)
                .contentType(APPLICATION_JSON)
                .json(registrations)
        },
        "/api/webinar/register" bind POST to request@{ request ->
            if (fubApiKey == null) {
                log.e { "Missing FUB_API_KEY environment variable" }
                return@request Response(BAD_REQUEST).json("Missing CRM api key".error)
            }

            val registration =
                try {
                    json.decodeFromString<Registration>(request.bodyString())
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).json("Invalid registration".error)
                }

            registrations += registration
            log.d { "New registration: $registration" }
            log.d { "Total registrations: ${registrations.size}" }

            val payload =
                FollowUpBossEvent(
                    person =
                        EventPerson(
                            registration.firstName,
                            registration.lastName,
                            listOf(EmailEntry(registration.email)),
                            listOf(PhoneEntry(registration.phone)),
                            source = "guptarealty.com/webinar",
                            tags = listOf("Webinar"),
                        ),
                    description = "Signed up for webinar \"$webinarName\"",
                )

            val request =
                Request(POST, "https://api.followupboss.com/v1/events")
                    .accept(APPLICATION_JSON)
                    .basicAuthentication(Credentials(fubApiKey, ""))
                    .contentType(APPLICATION_JSON)
                    .body(json.encodeToString(payload))

            client(request)
        },
    )
