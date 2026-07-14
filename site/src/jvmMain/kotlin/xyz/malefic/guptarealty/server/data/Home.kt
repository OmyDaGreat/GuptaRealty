package xyz.malefic.guptarealty.server.data

import xyz.malefic.guptarealty.model.HomeInfo
import xyz.malefic.guptarealty.server.util.file

var homeInfo by file(
    "home-info.json",
    HomeInfo(
        heroTitle = "Results You'll Love, Without the Guesswork",
        heroSubtitle = "I believe that achieving great results shouldn't come with a side of overwhelm. I’m here to streamline the entire process, giving you total clarity and confidence from day one.",
        heroImage = "/Logo.jpg",
        stats =
            Triple(
                "Backed by #1 Independent Brokerage in CA",
                "106,000+ dlients served broker-wide",
                "61+ office locations expanding across the US",
            ),
        statsNotice = "Brokerage stats provided by FTRE based on 2025 year-end data.",
        aboutTitle = "Hi! I'm Ruchika",
        aboutDescription =
            """
            I help buyers, and sellers navigate the OC real estate market with confidence — and I’ve been licensed since January 2022.
            Originally from India, I’ve called OC home for the past 20 years and have lived in Anaheim since then.

            I’m known for being personable, organized, and honest — but also a strong advocate when it matters most.
            Outside of real estate, I’m a big traveler. When I’m not working, you can usually find me playing board games with my kids or finding a new coffee shop somewhere.
            """.trimIndent(),
        aboutImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuCuwNDb-CwDlopOQd4M9z3qBsg47Jva-z3IKYTWAqKhXGqgBv2NtxGRCt-jSRpohSDwMsX40mGSIGNOz1apgYvVFiwjYWU-Hr9gDe9tl3LB2AtgcF9HBpYMqEc4hgpCT-QjcjVm9ziJAGwY14iXUG09Izkj-tWX-_1ms4BS2xhq1Lf7ZXLMJL9tpGfKAdYRfbEQb9HgLYxMpq20gtvpZPknpotYaCYkfxyGkojJSeOyL2LaDJEqrdnx7qKd-slF0Ub2NRLljwbqyEc",
        ctaTitle = "Looking for properties?",
        ctaDescription = "Access our exclusive database of local listings and find your perfect home before it even hits the open market.",
        ctaSearchLink = "/contact",
        ctaDownloadLink = "/contact",
    ),
)
