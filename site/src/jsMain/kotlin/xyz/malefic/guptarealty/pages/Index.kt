package xyz.malefic.guptarealty.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.aspectRatio
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.borderTop
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexWrap
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridColumn
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.letterSpacing
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.rotate
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.modifiers.textTransform
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.serialization.json.Json
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.model.BlogPost
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppModifiers
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.BodyLgStyle
import xyz.malefic.guptarealty.styles.BodyMdStyle
import xyz.malefic.guptarealty.styles.ContainerStyle
import xyz.malefic.guptarealty.styles.DisplayLgStyle
import xyz.malefic.guptarealty.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.styles.LabelMdStyle
import xyz.malefic.guptarealty.styles.LabelSmStyle
import xyz.malefic.guptarealty.styles.PrimaryButtonStyle
import xyz.malefic.guptarealty.styles.SecondaryButtonStyle
import xyz.malefic.guptarealty.styles.SectionStyle
import xyz.malefic.guptarealty.styles.ShowOnMdStyle

@Page
@Composable
fun HomePage() {
    Column(Modifier.fillMaxSize()) {
        HeroSection()
        AboutSection()
        BlogPreviewSection()
        CTASection()
    }
}

@Composable
fun HeroSection() {
    Box(SectionStyle.toModifier().backgroundColor(AppColors.SurfaceLow), contentAlignment = Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            SimpleGrid(
                numColumns(1, md = 2),
                Modifier.gap(AppSpacing.Gutter).alignItems(com.varabyte.kobweb.compose.css.AlignItems.Center),
            ) {
                Column(Modifier.padding(topBottom = AppSpacing.SectionGap)) {
                    H1(DisplayLgStyle.toModifier().margin(bottom = 24.px).toAttrs()) {
                        Text("Helping you find your way home in Orange County and beyond.")
                    }
                    P(
                        BodyLgStyle
                            .toModifier()
                            .color(AppColors.OnSurfaceVariant)
                            .margin(bottom = 32.px)
                            .toAttrs(),
                    ) {
                        Text("Expert guidance, local insight, and a personalized approach to your real estate journey.")
                    }
                    Row(Modifier.gap(16.px).flexWrap(FlexWrap.Wrap)) {
                        Link("/buy", PrimaryButtonStyle.toModifier()) {
                            Text("Search Homes")
                        }
                        Link("/contact", SecondaryButtonStyle.toModifier()) {
                            Text("Schedule Consultation")
                        }
                    }
                }
                Box(ShowOnMdStyle.toModifier().justifyContent(com.varabyte.kobweb.compose.css.JustifyContent.Center)) {
                    Box(
                        Modifier
                            .size(400.px)
                            .borderRadius(48.px)
                            .backgroundColor(AppColors.PrimaryFixed)
                            .rotate(3.deg),
                    )
                }
            }
        }
    }
}

@Composable
fun AboutSection() {
    Box(SectionStyle.toModifier(), contentAlignment = Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            SimpleGrid(
                numColumns(1, md = 12),
                Modifier.gap(AppSpacing.Gutter).alignItems(com.varabyte.kobweb.compose.css.AlignItems.Center),
            ) {
                Box(Modifier.gridColumn("span 5")) {
                    Image(
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCuwNDb-CwDlopOQd4M9z3qBsg47Jva-z3IKYTWAqKhXGqgBv2NtxGRCt-jSRpohSDwMsX40mGSIGNOz1apgYvVFiwjYWU-Hr9gDe9tl3LB2AtgcF9HBpYMqEc4hgpCT-QjcjVm9ziJAGwY14iXUG09Izkj-tWX-_1ms4BS2xhq1Lf7ZXLMJL9tpGfKAdYRfbEQb9HgLYxMpq20gtvpZPknpotYaCYkfxyGkojJSeOyL2LaDJEqrdnx7qKd-slF0Ub2NRLljwbqyEc",
                        "Interior",
                        Modifier
                            .fillMaxWidth()
                            .aspectRatio(4, 5)
                            .borderRadius(16.px)
                            .then(AppModifiers.SoftShadow),
                    )
                }
                Column(Modifier.gridColumn("span 7").padding(left = AppSpacing.S5)) {
                    Span(
                        LabelMdStyle
                            .toModifier()
                            .color(
                                AppColors.Secondary,
                            ).letterSpacing(0.2.em)
                            .textTransform(TextTransform.Uppercase)
                            .margin(bottom = 16.px)
                            .toAttrs(),
                    ) {
                        Text("Meet Ruchika")
                    }
                    H2(
                        DisplayLgStyle
                            .toModifier()
                            .fontSize(32.px)
                            .margin(bottom = 24.px)
                            .toAttrs(),
                    ) {
                        Text("Broker Associate & Realtor®")
                    }
                    P(BodyLgStyle.toModifier().margin(bottom = 24.px).toAttrs()) {
                        Text(
                            "I'm Ruchika, Broker Associate and Realtor® with a passion for helping families find their perfect home. My approach is built on trust, transparency, and a deep understanding of the emotional and financial significance of every transaction.",
                        )
                    }
                    P(BodyLgStyle.toModifier().toAttrs()) {
                        Text(
                            "Whether you're a first-time buyer or looking to sell your family estate, I provide the editorial-level marketing and boutique care your property deserves.",
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BlogPreviewSection() {
    val mockBlogJson =
        """
        [
            {
                "id": "1",
                "title": "Market Trends 2024",
                "summary": "Navigating the shifting landscape of Cheshire real estate this coming year...",
                "imageUrl": "https://lh3.googleusercontent.com/aida-public/AB6AXuCPQZL2d06Aa7tXDLhenIb7TMP3AlZsEgxvn6BmxXa4p8CtEwaidUKayGU9xEwH9DNifWfz76cqSg3eEZx3ACe5L_aESgI_5CrB58aCYrdz-AcUD1x3JBhNJ4UpA0kl1RreUGJ4mMwgSu0MvKiqsc_fLmAf_FIVlyC_1aoDZZPR9zDzQTXQNR8luWCnNXDlkAZhOQb8sjoVsZlpE6XwnmKj5Yis3rSucsMp1EIwL6HFctIC9ZMk_cUiYYCeiJweUgEFlFl5F-JItmY",
                "category": "Market Update",
                "date": "2024-01-01"
            },
            {
                "id": "2",
                "title": "Home Staging Tips",
                "summary": "How to maximize your home's appeal and sale price with minimal investment.",
                "imageUrl": "https://lh3.googleusercontent.com/aida-public/AB6AXuCtr9pJ0DNY8UIV7gtfBfxpuxgPe9OSLDMPmSEDEQAf2PtKbx9-PUUrzXgdT--9Ks1AHMiKSvH3GjzinqrPPZPpdMhZE2v47cutqbMzo4Y7wISrUUe1fjtevMCY_K0MZx0C5PM6UVqY49XWO48bY14PTzDpX8UtCL4ST6sk6q1an7ZiYPsvh_srX6ORzu82zJkC9oF-nWnmhoRs73s-BIzV3qhvjZjCfAEXeMth6yU114HTF31WvNqDEKo27mFv6_jaWh9gDHFYw74",
                "category": "Staging",
                "date": "2024-01-05"
            },
            {
                "id": "3",
                "title": "Buying vs Renting",
                "summary": "Making the right financial decision for your family's future in today's economy.",
                "imageUrl": "https://lh3.googleusercontent.com/aida-public/AB6AXuApF3zCv4r3yPfgXw5wgSnGKWm63gO0maISD20iFb-nq_z6A-tzk8KSSKf9slb15DvKCxkXDV2xTmG9xp-lFikYsklaMfCHe4Ptw-XSbOz8wtGAnq4vLQihVym2CkYvaFvT-X4Nx6d6U8sXS8AqpXb4Q2Aog_5_dkJvBzHCnuoFLgyC8Jq-0_5rljMAA2PpXclHbJZxiG81XRVM40HJh--uJp6yLunjybeWbuSicXLGGj24N5XlJnrkVhfR495WNpjz9vzzKdsGBgo",
                "category": "Guides",
                "date": "2024-01-10"
            }
        ]
        """.trimIndent()
    val posts = remember { Json.decodeFromString<List<BlogPost>>(mockBlogJson) }

    Box(
        SectionStyle.toModifier().backgroundColor(AppColors.SurfaceLowest).borderTop(1.px, LineStyle.Solid, AppColors.OutlineVariant),
        contentAlignment = Alignment.Center,
    ) {
        Box(ContainerStyle.toModifier()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .justifyContent(
                        com.varabyte.kobweb.compose.css.JustifyContent.SpaceBetween,
                    ).alignItems(com.varabyte.kobweb.compose.css.AlignItems.End)
                    .margin(bottom = 48.px),
            ) {
                Column {
                    Span(
                        LabelMdStyle
                            .toModifier()
                            .color(
                                AppColors.Secondary,
                            ).letterSpacing(0.2.em)
                            .textTransform(TextTransform.Uppercase)
                            .margin(bottom = 16.px)
                            .toAttrs(),
                    ) {
                        Text("Insights")
                    }
                    H2(DisplayLgStyle.toModifier().fontSize(32.px).toAttrs()) {
                        Text("The Realty Blog")
                    }
                }
                Link(
                    "/blog",
                    ShowOnMdStyle
                        .toModifier()
                        .color(
                            AppColors.Primary,
                        ).fontWeight(com.varabyte.kobweb.compose.css.FontWeight.Bold)
                        .textDecorationLine(TextDecorationLine.None),
                ) {
                    Text("View All Stories")
                }
            }

            SimpleGrid(numColumns(1, md = 3), Modifier.gap(AppSpacing.Gutter)) {
                posts.forEach { post ->
                    BlogCard(post)
                }
            }
        }
    }
}

@Composable
fun BlogCard(post: BlogPost) {
    Column(Modifier.fillMaxWidth().cursor(Cursor.Pointer)) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(16, 9)
                .borderRadius(12.px)
                .overflow(Overflow.Hidden)
                .margin(bottom = 16.px),
        ) {
            Image(post.imageUrl, post.title, Modifier.fillMaxSize().objectFit(ObjectFit.Cover))
            Box(
                Modifier
                    .position(
                        Position.Absolute,
                    ).top(
                        16.px,
                    ).left(16.px)
                    .backgroundColor(AppColors.PrimaryFixed)
                    .padding(topBottom = 4.px, leftRight = 12.px)
                    .borderRadius(50.px),
            ) {
                Span(LabelSmStyle.toModifier().color(AppColors.Primary).toAttrs()) { Text(post.category) }
            }
        }
        H3(HeadlineSmStyle.toModifier().margin(bottom = 8.px).toAttrs()) { Text(post.title) }
        P(
            BodyMdStyle
                .toModifier()
                .color(AppColors.OnSurfaceVariant)
                .margin(bottom = 16.px)
                .toAttrs(),
        ) { Text(post.summary) }
        Link(
            "#",
            Modifier
                .color(
                    AppColors.Secondary,
                ).fontWeight(com.varabyte.kobweb.compose.css.FontWeight.Bold)
                .textDecorationLine(TextDecorationLine.None),
        ) {
            Text("Read Story")
        }
    }
}

@Composable
fun CTASection() {
    Box(SectionStyle.toModifier(), contentAlignment = Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .backgroundColor(AppColors.Primary)
                    .borderRadius(48.px)
                    .padding(AppSpacing.S10)
                    .textAlign(com.varabyte.kobweb.compose.css.TextAlign.Center)
                    .position(Position.Relative)
                    .overflow(Overflow.Hidden),
            ) {
                H2(
                    DisplayLgStyle
                        .toModifier()
                        .color(AppColors.OnPrimary)
                        .margin(bottom = 24.px)
                        .toAttrs(),
                ) {
                    Text("Looking for properties?")
                }
                P(
                    BodyLgStyle
                        .toModifier()
                        .color(AppColors.OnPrimaryContainer)
                        .margin(bottom = 40.px)
                        .maxWidth(600.px)
                        .styleModifier {
                            property("margin-inline", "auto")
                        }.toAttrs(),
                ) {
                    Text("Access our exclusive database of local listings and find your perfect home before it even hits the open market.")
                }
                Row(Modifier.gap(16.px).justifyContent(com.varabyte.kobweb.compose.css.JustifyContent.Center).flexWrap(FlexWrap.Wrap)) {
                    Link("/buy", PrimaryButtonStyle.toModifier()) {
                        Text("Search Now")
                    }
                    Link("/contact", SecondaryButtonStyle.toModifier().color(AppColors.OnPrimary).border(color = AppColors.OnPrimary)) {
                        Text("Download Homebuyer Guide")
                    }
                }
            }
        }
    }
}
