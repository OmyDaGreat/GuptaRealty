package xyz.malefic.guptarealty.server.data

import xyz.malefic.guptarealty.model.SiteInfo
import xyz.malefic.guptarealty.server.util.file

var siteInfo by file(
    "site-info.json",
    SiteInfo(
        siteName = "Gupta Realty",
        agentName = "Ruchika Gupta",
        agentLicense = "DRE# 02161384",
        agentPhone = "714-767-5752",
        agentEmail = "ruchikagupta@firstteam.com",
        agentAddress = "Orange County, CA",
        brokerageName = "First Team Real Estate",
        footerDescription = "A real estate experience defined by sophistication, expertise, and personalized service.",
        logoUrl = "/Logo.jpg",
    ),
)
