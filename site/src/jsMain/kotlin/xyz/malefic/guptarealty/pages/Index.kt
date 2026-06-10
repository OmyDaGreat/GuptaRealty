package xyz.malefic.guptarealty.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
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
import xyz.malefic.guptarealty.api.getBlog
import xyz.malefic.guptarealty.components.Loading
import xyz.malefic.guptarealty.model.BlogPostResponse
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
                Modifier.gap(AppSpacing.Gutter).alignItems(AlignItems.Center),
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
                Box(ShowOnMdStyle.toModifier().justifyContent(JustifyContent.Center)) {
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
                Modifier.gap(AppSpacing.Gutter).alignItems(AlignItems.Center),
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
                            .color(AppColors.Secondary)
                            .letterSpacing(0.2.em)
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
    var posts by remember { mutableStateOf<List<BlogPostResponse>?>(null) }

    LaunchedEffect(null) {
        posts = getBlog().sortedByDescending { it.date }.subList(0, 3)
    }

    Loading(posts) { posts ->
        Box(
            SectionStyle
                .toModifier()
                .backgroundColor(AppColors.SurfaceLowest)
                .borderTop(1.px, LineStyle.Solid, AppColors.OutlineVariant),
            contentAlignment = Alignment.Center,
        ) {
            Box(ContainerStyle.toModifier()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .justifyContent(JustifyContent.SpaceBetween)
                        .alignItems(AlignItems.End)
                        .margin(bottom = 48.px),
                ) {
                    Column {
                        Span(
                            LabelMdStyle
                                .toModifier()
                                .color(AppColors.Secondary)
                                .letterSpacing(0.2.em)
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
                        "/blog/",
                        ShowOnMdStyle
                            .toModifier()
                            .color(AppColors.Primary)
                            .fontWeight(FontWeight.Bold)
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
}

@Composable
fun BlogCard(post: BlogPostResponse) {
    Column(Modifier.fillMaxWidth().cursor(Cursor.Pointer)) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(16, 9)
                .borderRadius(12.px)
                .overflow(Overflow.Hidden)
                .margin(bottom = 16.px),
        ) {
            Image(
                post.imageUrl ?: "https://upload.wikimedia.org/wikipedia/commons/7/70/Example.png", // TODO: Better default image
                post.title,
                Modifier.fillMaxSize().objectFit(ObjectFit.Cover),
            )
            Row(
                Modifier
                    .position(Position.Absolute)
                    .top(16.px)
                    .left(16.px)
                    .backgroundColor(AppColors.PrimaryFixed)
                    .padding(topBottom = 4.px, leftRight = 12.px)
                    .borderRadius(50.px),
            ) {
                post.tags.forEach {
                    Span(
                        LabelSmStyle
                            .toModifier()
                            .color(AppColors.OnPrimary)
                            .padding(2.px)
                            .toAttrs(),
                    ) { Text(it) }
                }
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
                .color(AppColors.Secondary)
                .fontWeight(FontWeight.Bold)
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
                    .textAlign(TextAlign.Center)
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
                Row(Modifier.gap(16.px).justifyContent(JustifyContent.Center).flexWrap(FlexWrap.Wrap)) {
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
