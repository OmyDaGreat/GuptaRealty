package xyz.malefic.guptarealty.server

import co.touchlab.kermit.Logger
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import xyz.malefic.guptarealty.model.Webinar

val webinar: Array<RoutingHttpHandler> =
    arrayOf(
        "/api/webinar" bind GET to { request ->
            var error: String? = null
            val timeZone =
                try {
                    request.query("tz")?.let { TimeZone.of(it) }
                        ?: TimeZone.currentSystemDefault().also {
                            error = "No time zone provided, defaulting to system time zone. "
                        }
                } catch (e: Exception) {
                    TimeZone.currentSystemDefault().also {
                        error = "Invalid time zone (${request.query("tz")}) provided, defaulting to system time zone. "
                    }
                }
            val instant = LocalDateTime(2026, 6, 23, 4, 0).toInstant(timeZone)

            error?.let { Logger.e(tag = "Webinar") { it } }

            Response(OK)
                .header("Content-Type", APPLICATION_JSON.value)
                .body(
                    json.encodeToString(
                        Webinar(
                            "FREE LIVE EDUCATIONAL SERIES",
                            "Mastering the Market: A Guide for First-Time Buyers",
                            "Join Ruchika Gupta for an exclusive deep dive into the 2026 real estate landscape. Learn how to navigate interest rates and find your dream home with confidence.",
                            instant,
                            "https://lh3.googleusercontent.com/aida-public/AB6AXuCM7sKezxKRo-EioXnRhIC_IiX-GdPFc7eJGkEWj4tKCl60ICSqdCPP35EQti9h7fRB4leoi2omg3ptiVBuVcxg8PALJfH_71PnmmxDcu8NUftKKF6pG4VBCQ8QqHpEELt4bQ7w_z8u_jMhn_0eSRxE-NiVhXHzTDIszoeKs_lIsbASO045cROxdCd64vQm-Mu3dwkTOJR225Mtw63B7WPBbI04mTSwLs792n6BuyBftL04Fl1z_hpwzwJi9uDEKj12VCtKrn8_dlY",
                        ),
                    ),
                )
        },
    )
