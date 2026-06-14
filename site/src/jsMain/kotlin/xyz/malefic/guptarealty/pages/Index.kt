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
import com.varabyte.kobweb.compose.css.WhiteSpace
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
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.FlexWrap
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
import xyz.malefic.guptarealty.api.getHomeSettings
import xyz.malefic.guptarealty.components.Loading
import xyz.malefic.guptarealty.model.BlogPostResponse
import xyz.malefic.guptarealty.model.HomeInfo
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppModifiers
import xyz.malefic.guptarealty.styles.AppRadius
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
import xyz.malefic.guptarealty.styles.SectionWarmStyle
import xyz.malefic.guptarealty.styles.ShowOnMdStyle

@Page
@Composable
fun HomePage() {
    var settings by remember { mutableStateOf<HomeInfo?>(null) }
    var posts by remember { mutableStateOf<List<BlogPostResponse>?>(null) }

    LaunchedEffect(Unit) {
        settings = getHomeSettings()
        posts = getBlog().sortedByDescending { it.date }.take(3)
    }

    Column(Modifier.fillMaxSize()) {
        HeroSection(settings)
        AboutSection(settings)
        BlogPreviewSection(posts)
        CTASection(settings)
    }
}

@Composable
fun HeroSection(settings: HomeInfo?) {
    Box(SectionStyle.toModifier(), contentAlignment = Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            SimpleGrid(
                numColumns(1, md = 2),
                Modifier.gap(AppSpacing.Gutter).alignItems(AlignItems.Center),
            ) {
                Column(Modifier.padding(topBottom = AppSpacing.SectionGap)) {
                    Loading(settings) {
                        H1(DisplayLgStyle.toModifier().margin(bottom = 24.px).toAttrs()) {
                            Text(heroTitle)
                        }
                        P(
                            BodyLgStyle
                                .toModifier()
                                .color(AppColors.OnSurfaceVariant)
                                .margin(bottom = 32.px)
                                .toAttrs(),
                        ) {
                            Text(heroSubtitle)
                        }
                    }
                    Row(Modifier.gap(16.px).flexWrap(FlexWrap.Wrap)) {
                        Link("https://example.com", PrimaryButtonStyle.toModifier()) {
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
                            .backgroundColor(AppColors.PrimaryContainer)
                            .rotate(3.deg)
                            .overflow(Overflow.Hidden),
                    ) {
                        Loading(settings) {
                            Image(
                                heroImage,
                                "Hero Image",
                                Modifier.fillMaxSize().objectFit(ObjectFit.Cover),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AboutSection(settings: HomeInfo?) {
    Box(SectionWarmStyle.toModifier(), Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            SimpleGrid(
                numColumns(1, md = 12),
                Modifier.gap(AppSpacing.Gutter).alignItems(AlignItems.Center),
            ) {
                Box(Modifier.gridColumn("span 5")) {
                    Loading(settings) {
                        Image(
                            aboutImage,
                            "About Image",
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(4, 5)
                                .borderRadius(AppRadius.Lg)
                                .then(AppModifiers.SoftShadow),
                        )
                    }
                }
                Column(Modifier.gridColumn("span 7").padding(left = AppSpacing.S5)) {
                    Loading(settings) {
                        Span(
                            LabelMdStyle
                                .toModifier()
                                .color(AppColors.Secondary)
                                .letterSpacing(0.2.em)
                                .textTransform(TextTransform.Uppercase)
                                .margin(bottom = 16.px)
                                .toAttrs(),
                        ) {
                            Text(aboutLabel)
                        }
                        H2(
                            DisplayLgStyle
                                .toModifier()
                                .fontSize(32.px)
                                .margin(bottom = 24.px)
                                .toAttrs(),
                        ) {
                            Text(aboutTitle)
                        }

                        P(BodyLgStyle.toModifier().whiteSpace(WhiteSpace.PreWrap).toAttrs()) {
                            Text(aboutDescription)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BlogPreviewSection(posts: List<BlogPostResponse>?) {
    Box(
        SectionStyle
            .toModifier()
            .backgroundColor(AppColors.SurfaceLowest),
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

            Loading(posts) {
                SimpleGrid(numColumns(1, md = 3), Modifier.gap(AppSpacing.Gutter)) {
                    this.forEach { post ->
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
                .borderRadius(AppRadius.Md)
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
                    .backgroundColor(AppColors.SecondaryContainer)
                    .padding(topBottom = 4.px, leftRight = 12.px)
                    .borderRadius(AppRadius.Full),
            ) {
                post.tags.forEach {
                    Span(
                        LabelSmStyle
                            .toModifier()
                            .color(AppColors.OnSecondaryContainer)
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
            "/blog/${post.id}",
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
fun CTASection(settings: HomeInfo?) {
    Box(SectionStyle.toModifier(), contentAlignment = Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .backgroundColor(AppColors.SecondaryFixed)
                    .borderRadius(AppRadius.Xl)
                    .padding(AppSpacing.S10)
                    .textAlign(TextAlign.Center)
                    .position(Position.Relative)
                    .overflow(Overflow.Hidden),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Loading(settings) {
                    H2(
                        DisplayLgStyle
                            .toModifier()
                            .color(AppColors.OnSecondaryFixed)
                            .margin(bottom = 24.px)
                            .toAttrs(),
                    ) {
                        Text(ctaTitle)
                    }
                    P(
                        BodyLgStyle
                            .toModifier()
                            .color(AppColors.OnSecondaryFixedVariant)
                            .margin(bottom = 40.px)
                            .maxWidth(600.px)
                            .styleModifier {
                                property("margin-inline", "auto")
                            }.toAttrs(),
                    ) {
                        Text(ctaDescription)
                    }
                    Row(Modifier.gap(16.px).justifyContent(JustifyContent.Center).flexWrap(FlexWrap.Wrap)) {
                        Link(ctaSearchLink, PrimaryButtonStyle.toModifier()) {
                            Text("Search Now")
                        }
                        Link(
                            ctaDownloadLink,
                            SecondaryButtonStyle.toModifier().color(AppColors.OnSecondaryFixed).border(color = AppColors.OnSecondaryFixed),
                        ) {
                            Text("Download Homebuyer Guide")
                        }
                    }
                }
            }
        }
    }
}
