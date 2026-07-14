package xyz.malefic.guptarealty.client.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import co.touchlab.kermit.Logger
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.client.api.getHomeInfo
import xyz.malefic.guptarealty.client.api.postHomeSettings
import xyz.malefic.guptarealty.client.components.AdminField
import xyz.malefic.guptarealty.client.components.AdminFieldNull
import xyz.malefic.guptarealty.client.components.AdminTextArea
import xyz.malefic.guptarealty.client.components.AssetLibrary
import xyz.malefic.guptarealty.client.components.Loading
import xyz.malefic.guptarealty.client.components.layouts.AdminLayoutData
import xyz.malefic.guptarealty.client.components.layouts.AdminLayoutScope
import xyz.malefic.guptarealty.client.components.layouts.AdminPage
import xyz.malefic.guptarealty.client.styles.AppColors
import xyz.malefic.guptarealty.client.styles.AppModifiers
import xyz.malefic.guptarealty.client.styles.AppRadius
import xyz.malefic.guptarealty.client.styles.AppSpacing
import xyz.malefic.guptarealty.client.styles.HeadlineMdStyle
import xyz.malefic.guptarealty.client.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.client.styles.SecondaryButtonStyle
import xyz.malefic.guptarealty.model.HomeInfo

@InitRoute
fun initIndexPage(ctx: InitRouteContext) {
    ctx.data.add(AdminLayoutData(AdminPage.HOME))
}

@Page
@Composable
fun AdminLayoutScope.HomePage() {
    val scope = rememberCoroutineScope()
    var settings by remember { mutableStateOf<HomeInfo?>(null) }
    var message by remember { mutableStateOf<Pair<String, Boolean>?>(null) }
    var selectingImageFor by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        settings = getHomeInfo()
    }

    Column(Modifier.fillMaxSize().overflow(Overflow.Auto).padding(AppSpacing.S4)) {
        H2(HeadlineMdStyle.toModifier().margin(bottom = AppSpacing.S4).toAttrs()) {
            Text("Home Page Settings")
        }

        message?.let { message ->
            P(
                Modifier
                    .color(if (message.second) AppColors.OnSecondaryContainer else AppColors.Error)
                    .backgroundColor(if (message.second) AppColors.SecondaryContainer else AppColors.ErrorContainer)
                    .padding(AppSpacing.S2)
                    .borderRadius(AppRadius.Default)
                    .margin(bottom = AppSpacing.S3)
                    .toAttrs(),
            ) {
                Text(message.first)
            }
        }

        Column(
            AppModifiers.Card
                .padding(AppSpacing.S4)
                .fillMaxWidth()
                .margin(bottom = AppSpacing.S4),
        ) {
            Loading(settings) {
                H2(HeadlineSmStyle.toModifier().margin(bottom = AppSpacing.S3).toAttrs()) {
                    Text("Hero Section")
                }
                AdminField("Hero Title", hero.title) {
                    settings = settings?.copy(hero = hero.copy(title = it))
                }
                AdminField("Hero Subtitle", hero.subtitle) {
                    settings = settings?.copy(hero = hero.copy(subtitle = it))
                }
                Row(
                    Modifier.gap(AppSpacing.S2),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AdminField("Hero Image URL", hero.image) {
                        settings = settings?.copy(hero = hero.copy(image = it))
                    }
                    Button(Modifier.onClick { selectingImageFor = "heroImage" }.toAttrs()) { Text("Pick") }
                }

                H2(HeadlineSmStyle.toModifier().margin(top = AppSpacing.S4, bottom = AppSpacing.S3).toAttrs()) {
                    Text("Stats Section")
                }
                AdminField("Stat 1", stats.first) {
                    settings = settings?.copy(stats = Triple(it, stats.second, stats.third))
                }
                AdminField("Stat 2", stats.second) {
                    settings = settings?.copy(stats = Triple(stats.first, it, stats.third))
                }
                AdminField("Stat 3", stats.third) {
                    settings = settings?.copy(stats = Triple(stats.first, stats.second, it))
                }
                AdminFieldNull("Stats Notice", statsNotice) {
                    settings = settings?.copy(statsNotice = it)
                }

                H2(HeadlineSmStyle.toModifier().margin(top = AppSpacing.S4, bottom = AppSpacing.S3).toAttrs()) {
                    Text("Help Section")
                }
                AdminField("Help Title", help.title) {
                    settings = settings?.copy(help = help.copy(title = it))
                }
                AdminTextArea("Help Description", help.description) {
                    settings = settings?.copy(help = help.copy(description = it))
                }

                H3(HeadlineSmStyle.toModifier().margin(top = AppSpacing.S2).toAttrs()) { Text("Help Box 1") }
                AdminField("Box 1 Title", help.boxes.first.title) {
                    settings =
                        settings?.copy(
                            help = help.copy(boxes = Pair(help.boxes.first.copy(title = it), help.boxes.second)),
                        )
                }
                AdminField("Box 1 Description", help.boxes.first.description) {
                    settings =
                        settings?.copy(
                            help = help.copy(boxes = Pair(help.boxes.first.copy(description = it), help.boxes.second)),
                        )
                }
                Row(
                    Modifier.gap(AppSpacing.S2),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AdminField("Box 1 Image URL", help.boxes.first.image) {
                        settings =
                            settings?.copy(
                                help = help.copy(boxes = Pair(help.boxes.first.copy(image = it), help.boxes.second)),
                            )
                    }
                    Button(Modifier.onClick { selectingImageFor = "helpBox1Image" }.toAttrs()) { Text("Pick") }
                }

                H3(HeadlineSmStyle.toModifier().margin(top = AppSpacing.S2).toAttrs()) { Text("Help Box 2") }
                AdminField("Box 2 Title", help.boxes.second.title) {
                    settings =
                        settings?.copy(
                            help = help.copy(boxes = Pair(help.boxes.first, help.boxes.second.copy(title = it))),
                        )
                }
                AdminField("Box 2 Description", help.boxes.second.description) {
                    settings =
                        settings?.copy(
                            help = help.copy(boxes = Pair(help.boxes.first, help.boxes.second.copy(description = it))),
                        )
                }
                Row(
                    Modifier.gap(AppSpacing.S2),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AdminField("Box 2 Image URL", help.boxes.second.image) {
                        settings =
                            settings?.copy(
                                help = help.copy(boxes = Pair(help.boxes.first, help.boxes.second.copy(image = it))),
                            )
                    }
                    Button(Modifier.onClick { selectingImageFor = "helpBox2Image" }.toAttrs()) { Text("Pick") }
                }

                H2(HeadlineSmStyle.toModifier().margin(top = AppSpacing.S4, bottom = AppSpacing.S3).toAttrs()) {
                    Text("About Section")
                }
                AdminField("About Title", about.title) {
                    settings = settings?.copy(about = about.copy(title = it))
                }
                AdminTextArea("About Description", about.description) {
                    settings = settings?.copy(about = about.copy(description = it))
                }
                Row(
                    Modifier.gap(AppSpacing.S2),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AdminField("About Image URL", about.image) {
                        settings = settings?.copy(about = about.copy(image = it))
                    }
                    Button(Modifier.onClick { selectingImageFor = "aboutImage" }.toAttrs()) { Text("Pick") }
                }

                H2(HeadlineSmStyle.toModifier().margin(top = AppSpacing.S4, bottom = AppSpacing.S3).toAttrs()) {
                    Text("Instagram Section")
                }
                AdminField("Insta Title", insta.title) {
                    settings = settings?.copy(insta = insta.copy(title = it))
                }
                AdminTextArea("Insta Description", insta.description) {
                    settings = settings?.copy(insta = insta.copy(description = it))
                }
                AdminField("Insta Follow Link", insta.followLink) {
                    settings = settings?.copy(insta = insta.copy(followLink = it))
                }
                AdminField("Insta Post 1 ID", insta.posts.first) {
                    settings = settings?.copy(insta = insta.copy(posts = Pair(it, insta.posts.second)))
                }
                AdminField("Insta Post 2 ID", insta.posts.second) {
                    settings = settings?.copy(insta = insta.copy(posts = Pair(insta.posts.first, it)))
                }

                H2(HeadlineSmStyle.toModifier().margin(top = AppSpacing.S4, bottom = AppSpacing.S3).toAttrs()) {
                    Text("YouTube Section")
                }
                AdminField("YouTube Title", youtube.title) {
                    settings = settings?.copy(youtube = youtube.copy(title = it))
                }
                AdminTextArea("YouTube Description", youtube.description) {
                    settings = settings?.copy(youtube = youtube.copy(description = it))
                }
                AdminField("YouTube Follow Link", youtube.followLink) {
                    settings = settings?.copy(youtube = youtube.copy(followLink = it))
                }
                AdminField("YouTube Post 1 ID", youtube.posts.first) {
                    settings = settings?.copy(youtube = youtube.copy(posts = Pair(it, youtube.posts.second)))
                }
                AdminField("YouTube Post 2 ID", youtube.posts.second) {
                    settings = settings?.copy(youtube = youtube.copy(posts = Pair(youtube.posts.first, it)))
                }

                H2(HeadlineSmStyle.toModifier().margin(top = AppSpacing.S4, bottom = AppSpacing.S3).toAttrs()) {
                    Text("Featured Testimonial")
                }
                AdminField("Author", testimonial.author) {
                    settings = settings?.copy(testimonial = testimonial.copy(author = it))
                }
                AdminTextArea("Quote", testimonial.quote) {
                    settings = settings?.copy(testimonial = testimonial.copy(quote = it))
                }
                Row(
                    Modifier.gap(AppSpacing.S2),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AdminFieldNull("Image URL", testimonial.imageSrc) {
                        settings = settings?.copy(testimonial = testimonial.copy(imageSrc = it))
                    }
                    Button(Modifier.onClick { selectingImageFor = "testimonialImage" }.toAttrs()) { Text("Pick") }
                }

                Button(
                    SecondaryButtonStyle
                        .toModifier()
                        .margin(top = AppSpacing.S3)
                        .toAttrs {
                            onClick {
                                scope.launch {
                                    message =
                                        try {
                                            postHomeSettings(settings!!, token)
                                            "Settings saved successfully" to true
                                        } catch (e: Exception) {
                                            Logger.e(e, "Index") { "Failed to save settings" }
                                            "Failed to save settings" to false
                                        }
                                }
                            }
                        },
                ) {
                    Text("Save All Settings")
                }
            }
        }

        AssetLibrary(token) { url ->
            settings =
                when (selectingImageFor) {
                    "heroImage" -> settings?.copy(hero = settings!!.hero.copy(image = url))
                    "aboutImage" -> settings?.copy(about = settings!!.about.copy(image = url))
                    "helpBox1Image" ->
                        settings?.copy(
                            help =
                                settings!!.help.copy(
                                    boxes = Pair(settings!!.help.boxes.first.copy(image = url), settings!!.help.boxes.second),
                                ),
                        )
                    "helpBox2Image" ->
                        settings?.copy(
                            help =
                                settings!!.help.copy(
                                    boxes = Pair(settings!!.help.boxes.first, settings!!.help.boxes.second.copy(image = url)),
                                ),
                        )
                    "testimonialImage" -> settings?.copy(testimonial = settings!!.testimonial.copy(imageSrc = url))
                    else -> settings
                }
            selectingImageFor = null
        }
    }
}
