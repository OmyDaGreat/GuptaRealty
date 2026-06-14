package xyz.malefic.guptarealty.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.api.getHomeSettings
import xyz.malefic.guptarealty.api.postHomeSettings
import xyz.malefic.guptarealty.components.AdminField
import xyz.malefic.guptarealty.components.AdminTextArea
import xyz.malefic.guptarealty.components.ImageLibrary
import xyz.malefic.guptarealty.components.Loading
import xyz.malefic.guptarealty.components.layouts.AdminLayoutData
import xyz.malefic.guptarealty.components.layouts.AdminLayoutScope
import xyz.malefic.guptarealty.components.layouts.AdminPage
import xyz.malefic.guptarealty.model.HomeInfo
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppModifiers
import xyz.malefic.guptarealty.styles.AppRadius
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.HeadlineMdStyle
import xyz.malefic.guptarealty.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.styles.SecondaryButtonStyle

@InitRoute
fun initIndexPage(ctx: InitRouteContext) {
    ctx.data.add(AdminLayoutData(AdminPage.HOME))
}

@Page
@Composable
fun AdminLayoutScope.HomePage() {
    var settings by remember { mutableStateOf<HomeInfo?>(null) }
    val scope = rememberCoroutineScope()
    var message by remember { mutableStateOf<Pair<String, Boolean>?>(null) }
    var selectingImageFor by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        settings = getHomeSettings()
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

        Loading(settings) { s ->
            Column(
                AppModifiers.Card
                    .padding(AppSpacing.S4)
                    .fillMaxWidth()
                    .margin(bottom = AppSpacing.S4),
            ) {
                H2(HeadlineSmStyle.toModifier().margin(bottom = AppSpacing.S3).toAttrs()) { Text("Hero Section") }
                AdminField("Hero Title", s.heroTitle) { settings = settings?.copy(heroTitle = it) }
                AdminField("Hero Subtitle", s.heroSubtitle) {
                    settings = settings?.copy(heroSubtitle = it)
                }
                Row(Modifier.gap(AppSpacing.S2), verticalAlignment = Alignment.CenterVertically) {
                    AdminField("Hero Image URL", s.heroImage) {
                        settings = settings?.copy(heroImage = it)
                    }
                    Button(Modifier.onClick { selectingImageFor = "heroImage" }.toAttrs()) { Text("Pick") }
                }

                H2(HeadlineSmStyle.toModifier().margin(top = AppSpacing.S4, bottom = AppSpacing.S3).toAttrs()) { Text("About Section") }
                AdminField("About Label", s.aboutLabel) {
                    settings = settings?.copy(aboutLabel = it)
                }
                AdminField("About Title", s.aboutTitle) {
                    settings = settings?.copy(aboutTitle = it)
                }
                AdminTextArea("About Description", s.aboutDescription) {
                    settings = settings?.copy(aboutDescription = it)
                }
                Row(Modifier.gap(AppSpacing.S2), verticalAlignment = Alignment.CenterVertically) {
                    AdminField("About Image URL", s.aboutImage) {
                        settings = settings?.copy(aboutImage = it)
                    }
                    Button(Modifier.onClick { selectingImageFor = "aboutImage" }.toAttrs()) { Text("Pick") }
                }

                H2(HeadlineSmStyle.toModifier().margin(top = AppSpacing.S4, bottom = AppSpacing.S3).toAttrs()) { Text("CTA Section") }
                AdminField("CTA Title", s.ctaTitle) { settings = settings?.copy(ctaTitle = it) }
                AdminField("CTA Description", s.ctaDescription) {
                    settings = settings?.copy(ctaDescription = it)
                }
                AdminField("CTA Search Link", s.ctaSearchLink) {
                    settings = settings?.copy(ctaSearchLink = it)
                }
                AdminField("CTA Download Link", s.ctaDownloadLink) {
                    settings = settings?.copy(ctaDownloadLink = it)
                }

                H2(HeadlineSmStyle.toModifier().margin(top = AppSpacing.S4, bottom = AppSpacing.S3).toAttrs()) { Text("Contact & Global") }
                AdminField("Email", s.contactEmail) { settings = settings?.copy(contactEmail = it) }
                AdminField("Phone", s.contactPhone) { settings = settings?.copy(contactPhone = it) }
                AdminField("Address", s.contactAddress) {
                    settings = settings?.copy(contactAddress = it)
                }
                AdminField("DRE#", s.dreNumber) { settings = settings?.copy(dreNumber = it) }

                Button(
                    SecondaryButtonStyle
                        .toModifier()
                        .margin(top = AppSpacing.S3)
                        .toAttrs {
                            onClick {
                                scope.launch {
                                    try {
                                        postHomeSettings(settings!!, token)
                                        message = "Settings saved successfully" to true
                                    } catch (e: Exception) {
                                        message = "Failed to save settings" to false
                                    }
                                }
                            }
                        },
                ) {
                    Text("Save All Settings")
                }
            }
        }

        ImageLibrary(token) { url ->
            when (selectingImageFor) {
                "aboutImage" -> settings = settings?.copy(aboutImage = url)
                "heroImage" -> settings = settings?.copy(heroImage = url)
            }
            selectingImageFor = null
        }
    }
}
