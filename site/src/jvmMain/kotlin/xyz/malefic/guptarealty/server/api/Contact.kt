package xyz.malefic.guptarealty.server.api

import co.touchlab.kermit.Logger
import org.http4k.client.OkHttp
import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.Credentials
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.accept
import org.http4k.lens.basicAuthentication
import org.http4k.lens.contentType
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import xyz.malefic.guptarealty.model.Contact
import xyz.malefic.guptarealty.model.EmailEntry
import xyz.malefic.guptarealty.model.EventPerson
import xyz.malefic.guptarealty.model.FollowUpBossEvent
import xyz.malefic.guptarealty.model.PhoneEntry
import xyz.malefic.guptarealty.model.json
import xyz.malefic.guptarealty.server.util.contains
import xyz.malefic.guptarealty.server.util.error
import xyz.malefic.guptarealty.server.util.fubApiKey

private val log = Logger.withTag("Contact")
private val client = OkHttp()

val contact: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/contact" bind POST to request@{ request ->
            if (fubApiKey == null) {
                log.e { "Missing FUB_API_KEY environment variable" }
                return@request error("Missing CRM api key")
            }

            val contact =
                try {
                    json.decodeFromString<Contact>(request.bodyString())
                } catch (_: Exception) {
                    return@request error("Invalid contact")
                }

            val payload =
                FollowUpBossEvent(
                    "guptarealty.com/contact",
                    type = "Inquiry",
                    person =
                        EventPerson(
                            contact.firstName,
                            contact.lastName,
                            listOf(EmailEntry(contact.email)),
                            listOf(PhoneEntry(contact.phone)),
                            source = "guptarealty.com/contact",
                            tags = listOf("Inquiry"),
                        ),
                    description =
                        contact.message
                            .trim()
                            .replace("\r\n", "\n")
                            .replace('\r', '\n')
                            .replace(Regex("[\\u0000-\\u0008\\u000B\\u000C\\u000E-\\u001F\\u007F]"), "")
                            .take(1000),
                )

            val request =
                Request(POST, "https://api.followupboss.com/v1/events")
                    .accept(APPLICATION_JSON)
                    .basicAuthentication(Credentials(fubApiKey, ""))
                    .contentType(APPLICATION_JSON)
                    .body(json.encodeToString(payload))

            val response = client(request)

            if (response.status !in Status.SUCCESSFUL) {
                log.e { "Failed to send contact to FollowUpBoss; returned\n${response.toMessage()}" }
                return@request error("Failed to send contact to FollowUpBoss")
            }

            Response(OK)
        },
    )
