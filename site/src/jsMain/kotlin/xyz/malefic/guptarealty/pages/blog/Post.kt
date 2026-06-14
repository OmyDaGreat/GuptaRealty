package xyz.malefic.guptarealty.pages.blog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.aspectRatio
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.mdi.MdiArrowBack
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.api.getBlog
import xyz.malefic.guptarealty.components.Loading
import xyz.malefic.guptarealty.components.MarkdownContent
import xyz.malefic.guptarealty.model.BlogPostResponse
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppRadius
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.BodyMdStyle
import xyz.malefic.guptarealty.styles.ContainerStyle
import xyz.malefic.guptarealty.styles.DisplayLgStyle
import xyz.malefic.guptarealty.styles.LabelMdStyle
import xyz.malefic.guptarealty.styles.StatusChipTertiaryStyle
import kotlin.uuid.Uuid

@Page("{id}")
@Composable
fun IndividualPostPage(ctx: PageContext) {
    val id = ctx.route.params["id"]
    var post by remember { mutableStateOf<BlogPostResponse?>(null) }

    LaunchedEffect(id) {
        id?.let {
            post = getBlog(Uuid.parse(it))
        }
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(ContainerStyle.toModifier().padding(topBottom = AppSpacing.SectionGap)) {
            Link(
                "/blog/",
                Modifier
                    .margin(bottom = 32.px)
                    .color(AppColors.Primary)
                    .textDecorationLine(TextDecorationLine.None),
            ) {
                Row(Modifier.gap(8.px), verticalAlignment = Alignment.CenterVertically) {
                    MdiArrowBack()
                    Span(LabelMdStyle.toModifier().toAttrs()) {
                        Text("Back to Blog")
                    }
                }
            }

            Loading(post) { blogPost ->
                Column(Modifier.fillMaxWidth()) {
                    Row(Modifier.gap(8.px).margin(bottom = 16.px)) {
                        blogPost.tags.forEach { tag ->
                            Span(
                                StatusChipTertiaryStyle
                                    .toModifier()
                                    .toAttrs(),
                            ) { Text(tag) }
                        }
                    }

                    H1(DisplayLgStyle.toModifier().margin(bottom = 16.px).toAttrs()) {
                        Text(blogPost.title)
                    }

                    Span(
                        BodyMdStyle
                            .toModifier()
                            .color(AppColors.OnSurfaceVariant)
                            .margin(bottom = 48.px)
                            .toAttrs(),
                    ) {
                        Text("Published on ${blogPost.date}")
                    }

                    if (blogPost.imageUrl != null) {
                        Image(
                            blogPost.imageUrl,
                            blogPost.title,
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(21, 9)
                                .borderRadius(AppRadius.Lg)
                                .objectFit(ObjectFit.Cover)
                                .margin(bottom = 48.px),
                        )
                    }

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .maxWidth(800.px)
                            .align(Alignment.CenterHorizontally),
                    ) {
                        MarkdownContent(
                            blogPost.content,
                            Modifier
                                .fillMaxWidth()
                                .color(AppColors.OnSurface)
                                .fontSize(18.px)
                                .lineHeight(1.8),
                        )
                    }
                }
            }
        }
    }
}
