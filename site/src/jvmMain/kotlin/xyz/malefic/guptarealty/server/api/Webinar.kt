package xyz.malefic.guptarealty.server.api

import co.touchlab.kermit.Logger
import org.http4k.client.OkHttp
import org.http4k.core.Body
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
import xyz.malefic.guptarealty.model.json
import xyz.malefic.guptarealty.server.data.currentWebinar
import xyz.malefic.guptarealty.server.data.registrations
import xyz.malefic.guptarealty.server.data.webinarName
import xyz.malefic.guptarealty.server.data.webinarReviews
import xyz.malefic.guptarealty.server.data.webinarTips

private val log = Logger.withTag("Webinar")

private val apiKey: String? =
    System.getProperty("FUB_API_KEY")
        ?: System.getenv("FUB_API_KEY")
private val client = OkHttp()

val webinar: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/webinar" bind GET to { request ->
            Response(OK)
                .contentType(APPLICATION_JSON)
                .body(json.encodeToString(currentWebinar))
        },
        "/api/webinar" bind GET to request@{ request ->
            currentWebinar =
                try {
                    json.decodeFromString(request.bodyString())
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).body("Invalid webinar")
                }

            Response(OK)
        },
        "/api/webinar/tips" bind GET to {
            Response(OK)
                .contentType(APPLICATION_JSON)
                .body(json.encodeToString(webinarTips))
        },
        "/api/webinar/tips" bind POST to request@{ request ->
            webinarTips =
                try {
                    json.decodeFromString(request.bodyString())
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).body("Invalid webinar tips")
                }

            Response(OK)
        },
        "/api/webinar/reviews" bind GET to {
            Response(OK)
                .contentType(APPLICATION_JSON)
                .body(json.encodeToString(webinarReviews))
        },
        "/api/webinar/reviews" bind POST to request@{ request ->
            webinarReviews =
                try {
                    json.decodeFromString(request.bodyString())
                } catch (e: Exception) {
                    return@request Response(BAD_REQUEST).body("Invalid webinar reviews")
                }

            Response(OK)
        },
        "/api/webinar/registrations" bind GET to request@{ request ->
            Response(OK)
                .contentType(APPLICATION_JSON)
                .body(json.encodeToString(registrations))
        },
        "/api/webinar/register" bind POST to request@{ request ->
            val registration =
                try {
                    json.decodeFromString<Registration>(request.bodyString())
                } catch (e: Exception) {
                    log.e(e) { "Failed to decode registration" }
                    null
                }

            registration?.let {
                registrations += registration
                log.d { "New registration: $registration" }
                log.d { "Total registrations: ${registrations.size}" }
                if (apiKey == null) {
                    log.e { "Missing FUB_API_KEY environment variable" }
                    return@request Response(BAD_REQUEST).body("Missing CRM api key")
                }

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
                        .basicAuthentication(Credentials(apiKey, ""))
                        .contentType(APPLICATION_JSON)
                        .body(Body(json.encodeToString(payload)))

                client(request)
            } ?: Response(BAD_REQUEST).body("Failed to decode registration")
        },
    )
