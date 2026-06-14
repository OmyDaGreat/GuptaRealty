package xyz.malefic.guptarealty.pages.blog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.varabyte.kobweb.compose.ui.modifiers.aspectRatio
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.letterSpacing
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.modifiers.textTransform
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.api.getBlog
import xyz.malefic.guptarealty.components.Loading
import xyz.malefic.guptarealty.model.BlogPostResponse
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppRadius
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.BodyLgStyle
import xyz.malefic.guptarealty.styles.BodyMdStyle
import xyz.malefic.guptarealty.styles.ContainerStyle
import xyz.malefic.guptarealty.styles.DisplayLgStyle
import xyz.malefic.guptarealty.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.styles.LabelMdStyle
import xyz.malefic.guptarealty.styles.PropertyCardStyle
import xyz.malefic.guptarealty.styles.SectionStyle
import xyz.malefic.guptarealty.styles.StatusChipTertiaryStyle

@Page
@Composable
fun BlogIndexPage() {
    var posts by remember { mutableStateOf<List<BlogPostResponse>?>(null) }

    LaunchedEffect(Unit) {
        posts = getBlog().sortedByDescending { it.date }
    }

    Column(Modifier.fillMaxSize()) {
        BlogHeroSection()

        Box(
            SectionStyle.toModifier().backgroundColor(AppColors.SurfaceLowest),
            contentAlignment = Alignment.Center,
        ) {
            Box(ContainerStyle.toModifier()) {
                Loading(posts) {
                    if (this.isEmpty()) {
                        Box(Modifier.fillMaxWidth().padding(topBottom = 100.px), contentAlignment = Alignment.Center) {
                            P(BodyLgStyle.toModifier().toAttrs()) {
                                Text("No blog posts found.")
                            }
                        }
                    } else {
                        SimpleGrid(
                            numColumns(1, sm = 2, md = 3),
                            Modifier.gap(AppSpacing.Gutter).padding(topBottom = AppSpacing.SectionGap),
                        ) {
                            this.forEach { post ->
                                BlogCard(post)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BlogHeroSection() {
    Box(
        SectionStyle.toModifier().backgroundColor(AppColors.SurfaceLow),
        contentAlignment = Alignment.Center,
    ) {
        Box(ContainerStyle.toModifier().padding(topBottom = AppSpacing.SectionGap)) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Span(
                    LabelMdStyle
                        .toModifier()
                        .color(AppColors.Secondary)
                        .letterSpacing(0.2.em)
                        .textTransform(TextTransform.Uppercase)
                        .margin(bottom = 16.px)
                        .toAttrs(),
                ) {
                    Text("Insights & Updates")
                }
                H1(
                    DisplayLgStyle
                        .toModifier()
                        .textAlign(TextAlign.Center)
                        .margin(bottom = 24.px)
                        .toAttrs(),
                ) {
                    Text("The Realty Blog")
                }
                P(
                    BodyLgStyle
                        .toModifier()
                        .color(AppColors.OnSurfaceVariant)
                        .textAlign(TextAlign.Center)
                        .maxWidth(600.px)
                        .toAttrs(),
                ) {
                    Text("Expert guidance, market trends, and local lifestyle insights from Ruchika Gupta and the team.")
                }
            }
        }
    }
}

@Composable
fun BlogCard(post: BlogPostResponse) {
    Link(
        "/blog/${post.id}",
        Modifier.textDecorationLine(TextDecorationLine.None).fillMaxWidth(),
    ) {
        Column(
            PropertyCardStyle
                .toModifier()
                .fillMaxWidth(),
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(16, 9)
                    .borderRadius(AppRadius.Md)
                    .overflow(Overflow.Hidden),
            ) {
                Image(
                    post.imageUrl ?: "https://upload.wikimedia.org/wikipedia/commons/7/70/Example.png", // TODO: Better placeholder
                    post.title,
                    Modifier.fillMaxSize().objectFit(ObjectFit.Cover),
                )
            }

            Column(Modifier.padding(AppSpacing.S4)) {
                Row(Modifier.gap(8.px).margin(bottom = 12.px)) {
                    post.tags.take(2).forEach { tag ->
                        Span(
                            StatusChipTertiaryStyle
                                .toModifier()
                                .toAttrs(),
                        ) { Text(tag) }
                    }
                }

                H3(
                    HeadlineSmStyle
                        .toModifier()
                        .margin(bottom = 8.px)
                        .toAttrs(),
                ) {
                    Text(post.title)
                }

                P(
                    BodyMdStyle
                        .toModifier()
                        .color(AppColors.OnSurfaceVariant)
                        .margin(bottom = 16.px)
                        .toAttrs(),
                ) {
                    Text(post.summary)
                }

                Row(Modifier.gap(8.px), verticalAlignment = Alignment.CenterVertically) {
                    Span(
                        LabelMdStyle
                            .toModifier()
                            .color(AppColors.Primary)
                            .toAttrs(),
                    ) {
                        Text("Read More")
                    }
                }
            }
        }
    }
}
