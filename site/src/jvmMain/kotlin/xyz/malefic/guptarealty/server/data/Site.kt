package xyz.malefic.guptarealty.server.data

import xyz.malefic.guptarealty.model.SiteInfo
import xyz.malefic.guptarealty.server.util.file

var siteInfo by file(
    "site-info.json",
    SiteInfo(
        siteName = "Gupta Realty",
        agentName = "Ruchika Gupta",
        agentLicense = "DRE #02161384",
        agentPhone = "714-767-5752",
        agentEmail = "ruchikagupta@firstteam.com",
        agentAddress = "Orange County, CA",
        brokerageName = "First Team Real Estate",
        brokerageLicense = "[First Team's DRE #]",
        footerDescription = "A real estate experience defined by sophistication, expertise, and personalized service.",
        logoUrl = "/Logo.jpg",
        affiliationLogos =
            listOf(
                "https://media-production.lp-cdn.com/cdn-cgi/image/format=auto,quality=85,fit=scale-down,width=960/https://media-production.lp-cdn.com/media/7d3ff192-7c1e-4f27-ad8c-1b946291eb0a",
                "/LuxuryPresence.png",
            ),
        tiktokUrl = "https://tiktok.com",
        instagramUrl = "https://instagram.com",
        linkedinUrl = "https://linkedin.com",
        disclaimerText = "Equal Housing Opportunity. All information provided is deemed reliable but is not guaranteed and should be independently verified. This is not intended as legal, tax, or financial advice. If you are currently working with another real estate agent under a signed representation agreement, this is not a solicitation of that business relationship.",
    ),
)
