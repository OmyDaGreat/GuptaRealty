package xyz.malefic.guptarealty.server.data

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import xyz.malefic.guptarealty.model.Registration
import xyz.malefic.guptarealty.model.Webinar
import xyz.malefic.guptarealty.model.WebinarReview
import xyz.malefic.guptarealty.model.WebinarTip
import xyz.malefic.guptarealty.model.WebinarTipsSection
import xyz.malefic.guptarealty.server.util.file

var registrations by file("registrations.json", HashMap<String, ArrayList<Registration>>())
var webinarTips by file(
    "webinar-tips.json",
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
)
var webinarReviews by file(
    "webinar-reviews.json",
    listOf(
        WebinarReview(
            "Mark & Sarah Thompson",
            "New Homeowners, Irvine",
            "Ruchika's webinar was the turning point for us. She simplified the escrow process and gave us the confidence to put in our first offer. We closed last month!",
        ),
        WebinarReview(
            "idk some reviewer",
            "Retired, Anaheim",
            "Some rly rly long review ahhhgiweajifgjwalfjwal",
        ),
    ),
)
var currentWebinar by file(
    "current-webinar.json",
    Webinar(
        "FREE LIVE EDUCATIONAL SERIES",
        "Mastering the Market: A Guide for First-Time Buyers",
        "Join Ruchika Gupta for an exclusive deep dive into the 2026 real estate landscape. Learn how to navigate interest rates and find your dream home with confidence.",
        LocalDateTime(2026, 6, 23, 4, 0).toInstant(TimeZone.currentSystemDefault()),
        "https://lh3.googleusercontent.com/aida-public/AB6AXuCM7sKezxKRo-EioXnRhIC_IiX-GdPFc7eJGkEWj4tKCl60ICSqdCPP35EQti9h7fRB4leoi2omg3ptiVBuVcxg8PALJfH_71PnmmxDcu8NUftKKF6pG4VBCQ8QqHpEELt4bQ7w_z8u_jMhn_0eSRxE-NiVhXHzTDIszoeKs_lIsbASO045cROxdCd64vQm-Mu3dwkTOJR225Mtw63B7WPBbI04mTSwLs792n6BuyBftL04Fl1z_hpwzwJi9uDEKj12VCtKrn8_dlY",
    ),
)
