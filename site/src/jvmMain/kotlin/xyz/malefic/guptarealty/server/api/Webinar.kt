package xyz.malefic.guptarealty.server.api

import co.touchlab.kermit.Logger
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import xyz.malefic.guptarealty.model.Registration
import xyz.malefic.guptarealty.model.Webinar
import xyz.malefic.guptarealty.model.WebinarReview
import xyz.malefic.guptarealty.model.WebinarTip
import xyz.malefic.guptarealty.model.WebinarTipsSection
import xyz.malefic.guptarealty.model.json
import xyz.malefic.guptarealty.server.data.registrations
import xyz.malefic.guptarealty.server.data.webinarName

private val log = Logger.withTag("Webinar")

val webinar: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/webinar" bind GET to { request ->
            val timeZone =
                try {
                    request.query("tz")?.let { TimeZone.of(it) }
                        ?: run {
                            log.e { "No time zone provided, defaulting to system time zone." }
                            TimeZone.currentSystemDefault()
                        }
                } catch (e: Exception) {
                    log.e { "Invalid time zone (${request.query("tz")}) provided, defaulting to system time zone." }
                    TimeZone.currentSystemDefault()
                }
            val instant = LocalDateTime(2026, 6, 23, 4, 0).toInstant(timeZone)

            Response(OK)
                .header("Content-Type", APPLICATION_JSON.value)
                .body(
                    json.encodeToString(
                        Webinar(
                            "FREE LIVE EDUCATIONAL SERIES",
                            webinarName,
                            "Join Ruchika Gupta for an exclusive deep dive into the 2026 real estate landscape. Learn how to navigate interest rates and find your dream home with confidence.",
                            instant,
                            "https://lh3.googleusercontent.com/aida-public/AB6AXuCM7sKezxKRo-EioXnRhIC_IiX-GdPFc7eJGkEWj4tKCl60ICSqdCPP35EQti9h7fRB4leoi2omg3ptiVBuVcxg8PALJfH_71PnmmxDcu8NUftKKF6pG4VBCQ8QqHpEELt4bQ7w_z8u_jMhn_0eSRxE-NiVhXHzTDIszoeKs_lIsbASO045cROxdCd64vQm-Mu3dwkTOJR225Mtw63B7WPBbI04mTSwLs792n6BuyBftL04Fl1z_hpwzwJi9uDEKj12VCtKrn8_dlY",
                        ),
                    ),
                )
        },
        "/api/webinar/tips" bind GET to {
            Response(OK)
                .header("Content-Type", APPLICATION_JSON.value)
                .body(
                    json.encodeToString(
                        WebinarTipsSection(
                            "Common First Time Home Buyer Mistakes",
                            listOf(
                                WebinarTip(
                                    "account_balance_wallet",
                                    "Skipping Pre-Approval",
                                    "Don't start the hunt without knowing your budget. Pre-approval gives you leverage and clarity in a competitive market.",
                                ),
                                WebinarTip(
                                    "home_work",
                                    "Ignoring Extra Costs",
                                    "Beyond the mortgage, consider closing costs, inspections, and insurance. We help you map out the full financial picture.",
                                ),
                                WebinarTip(
                                    "psychology",
                                    "Emotional Over-Investing",
                                    "It's easy to fall for a aesthetic and ignore structural red flags. We keep you grounded in data and long-term value.",
                                ),
                            ),
                        ),
                    ),
                )
        },
        "/api/webinar/reviews" bind GET to {
            Response(OK)
                .header("Content-Type", APPLICATION_JSON.value)
                .body(
                    json.encodeToString(
                        listOf(
                            WebinarReview(
                                "Mark & Sarah Thompson",
                                "New Homeowners, Irvine",
                                "https://lh3.googleusercontent.com/aida-public/AB6AXuDABXqZiK9yZKb-RRKmDrqGBFGreGt177dM_PlZcxT6ZuOHNlEbRD_RmtkCEUBpB3CWLb4JPdkNG4Kajom_60ccRqKUfMfkWndi0ZX_iRCqYTru7xuMJW7nn9gnIgnkEufgFEAqeADrQT1QDahHTkRtzVOTr5FnhUOLtA2pS0IN7G6r56Cb-LZhhvqgSjmcCmegKAdHUMIrcNZsYMteuRNNEC46OZH_gZeFvWYwFE7ne8efX6nBMDm2-LmQ3FAEdGctPB1w0BUuuGw",
                                "Ruchika's webinar was the turning point for us. She simplified the escrow process and gave us the confidence to put in our first offer. We closed last month!",
                            ),
                            WebinarReview(
                                "idk some reviewer",
                                "Retired, Anaheim",
                                "https://upload.wikimedia.org/wikipedia/commons/7/70/Example.png",
                                "Some rly rly long review ahhhgiweajifgjwalfjwal",
                            ),
                        ),
                    ),
                )
        },
        "/api/webinar/register" bind POST to { request ->
            val registration =
                try {
                    json.decodeFromString<Registration>(request.bodyString())
                } catch (e: Exception) {
                    log.e(e) { "Failed to decode registration" }
                    null
                }

            registration?.let {
                registrations += registration
                log.i { "New registration: $registration" }
                log.i { "Total registrations: ${registrations.size}" }
                Response(OK)
            } ?: Response(BAD_REQUEST).body("Failed to decode registration")
        },
    )
