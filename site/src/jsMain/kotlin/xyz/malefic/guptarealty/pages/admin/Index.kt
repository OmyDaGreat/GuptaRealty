package xyz.malefic.guptarealty.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.TextArea
import xyz.malefic.guptarealty.api.getWebinar
import xyz.malefic.guptarealty.api.getWebinarTips
import xyz.malefic.guptarealty.api.postWebinar
import xyz.malefic.guptarealty.api.postWebinarTips
import xyz.malefic.guptarealty.components.Loading
import xyz.malefic.guptarealty.components.layouts.AdminLayoutData
import xyz.malefic.guptarealty.components.layouts.AdminLayoutScope
import xyz.malefic.guptarealty.components.layouts.AdminPage
import xyz.malefic.guptarealty.model.Webinar
import xyz.malefic.guptarealty.model.WebinarTip
import xyz.malefic.guptarealty.model.WebinarTipsSection
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppModifiers
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.HeadlineMdStyle
import xyz.malefic.guptarealty.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.styles.LabelMdStyle
import kotlin.time.Instant

@InitRoute
fun initIndexPage(ctx: InitRouteContext) {
    ctx.data.add(AdminLayoutData(AdminPage.INDEX))
}

@Page
@Composable
fun AdminLayoutScope.IndexPage() {
    var webinar by remember { mutableStateOf<Webinar?>(null) }
    var tipsSection by remember { mutableStateOf<WebinarTipsSection?>(null) }
    val scope = rememberCoroutineScope()
    var message by remember { mutableStateOf<Pair<String, Boolean>?>(null) }

    LaunchedEffect(Unit) {
        webinar = getWebinar()
        tipsSection = getWebinarTips()
    }

    Column(Modifier.fillMaxSize().overflow(Overflow.Auto).padding(AppSpacing.S4)) {
        H2(HeadlineMdStyle.toModifier().margin(bottom = AppSpacing.S4).toAttrs()) {
            Text("Webinar Settings")
        }

        if (message != null) {
            P(
                Modifier
                    .color(if (message!!.second) AppColors.OnPrimaryContainer else AppColors.Error)
                    .backgroundColor(if (message!!.second) AppColors.PrimaryContainer else AppColors.ErrorContainer)
                    .padding(AppSpacing.S2)
                    .borderRadius(8.px)
                    .margin(bottom = AppSpacing.S3)
                    .toAttrs(),
            ) {
                Text(message!!.first)
            }
        }

        Loading(webinar) { currentWebinar ->
            Column(
                AppModifiers.Card
                    .padding(AppSpacing.S4)
                    .fillMaxWidth()
                    .margin(bottom = AppSpacing.S4),
            ) {
                H2(HeadlineSmStyle.toModifier().margin(bottom = AppSpacing.S3).toAttrs()) { Text("Main Info") }

                AdminField("Header", currentWebinar.header) { webinar = webinar?.copy(header = it) }
                AdminField("Title", currentWebinar.title) { webinar = webinar?.copy(title = it) }
                AdminTextArea("Description", currentWebinar.description) { webinar = webinar?.copy(description = it) }
                AdminField("Image URL", currentWebinar.imageUrl) { webinar = webinar?.copy(imageUrl = it) }
                AdminField("Date (ISO)", currentWebinar.date.toString()) {
                    try {
                        webinar = webinar?.copy(date = Instant.parse(it))
                    } catch (e: Exception) {
                    }
                }

                Button(
                    Modifier
                        .margin(top = AppSpacing.S3)
                        .backgroundColor(AppColors.Primary)
                        .color(AppColors.OnPrimary)
                        .padding(AppSpacing.S2, AppSpacing.S4)
                        .borderRadius(50.px)
                        .border(0.px)
                        .cursor(Cursor.Pointer)
                        .fontWeight(FontWeight.Bold)
                        .toAttrs {
                            onClick {
                                scope.launch {
                                    try {
                                        postWebinar(webinar!!, token)
                                        message = "Webinar updated successfully" to true
                                    } catch (e: Exception) {
                                        message = "Failed to update webinar" to false
                                    }
                                }
                            }
                        },
                ) {
                    Text("Save Webinar Info")
                }
            }
        }

        Loading(tipsSection) { currentTips ->
            Column(AppModifiers.Card.padding(AppSpacing.S4).fillMaxWidth()) {
                H2(HeadlineSmStyle.toModifier().margin(bottom = AppSpacing.S3).toAttrs()) { Text("Tips Section") }

                AdminField("Section Header", currentTips.header) {
                    tipsSection = tipsSection?.copy(header = it)
                }

                currentTips.mistakes.forEachIndexed { index, tip ->
                    Column(
                        Modifier
                            .margin(top = AppSpacing.S3)
                            .padding(AppSpacing.S2)
                            .border(1.px, LineStyle.Dashed, AppColors.OutlineVariant)
                            .borderRadius(8.px),
                    ) {
                        Row(Modifier.fillMaxWidth().margin(bottom = AppSpacing.S2), verticalAlignment = Alignment.CenterVertically) {
                            H2(LabelMdStyle.toModifier().toAttrs()) { Text("Tip #${index + 1}") }
                            Box(Modifier.weight(1f))
                            Button(
                                Modifier
                                    .backgroundColor(Colors.Transparent)
                                    .color(AppColors.Error)
                                    .border(0.px)
                                    .cursor(Cursor.Pointer)
                                    .toAttrs {
                                        onClick {
                                            tipsSection =
                                                tipsSection?.copy(
                                                    mistakes = currentTips.mistakes.toMutableList().apply { removeAt(index) },
                                                )
                                        }
                                    },
                            ) { Text("Remove") }
                        }
                        AdminField("Icon", tip.icon) {
                            tipsSection =
                                tipsSection?.copy(
                                    mistakes = currentTips.mistakes.toMutableList().apply { this[index] = tip.copy(icon = it) },
                                )
                        }
                        AdminField("Title", tip.title) {
                            tipsSection =
                                tipsSection?.copy(
                                    mistakes = currentTips.mistakes.toMutableList().apply { this[index] = tip.copy(title = it) },
                                )
                        }
                        AdminTextArea("Description", tip.description) {
                            tipsSection =
                                tipsSection?.copy(
                                    mistakes = currentTips.mistakes.toMutableList().apply { this[index] = tip.copy(description = it) },
                                )
                        }
                    }
                }

                Button(
                    Modifier
                        .margin(top = AppSpacing.S3)
                        .backgroundColor(AppColors.Secondary)
                        .color(AppColors.OnSecondary)
                        .padding(AppSpacing.S2, AppSpacing.S4)
                        .borderRadius(50.px)
                        .border(0.px)
                        .cursor(Cursor.Pointer)
                        .toAttrs {
                            onClick {
                                tipsSection =
                                    tipsSection?.copy(
                                        mistakes = tipsSection!!.mistakes + WebinarTip("help", "New Tip", "Description..."),
                                    )
                            }
                        },
                ) { Text("Add Tip") }

                Button(
                    Modifier
                        .margin(top = AppSpacing.S3)
                        .backgroundColor(AppColors.Primary)
                        .color(AppColors.OnPrimary)
                        .padding(AppSpacing.S2, AppSpacing.S4)
                        .borderRadius(50.px)
                        .border(0.px)
                        .cursor(Cursor.Pointer)
                        .fontWeight(FontWeight.Bold)
                        .toAttrs {
                            onClick {
                                scope.launch {
                                    try {
                                        postWebinarTips(tipsSection!!, token)
                                        message = "Tips updated successfully" to true
                                    } catch (e: Exception) {
                                        message = "Failed to update tips" to false
                                    }
                                }
                            }
                        },
                ) {
                    Text("Save Tips")
                }
            }
        }
    }
}

@Composable
fun AdminField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(Modifier.fillMaxWidth().margin(bottom = AppSpacing.S2)) {
        Label(attrs = LabelMdStyle.toModifier().margin(bottom = AppSpacing.S1).toAttrs()) { Text(label) }
        Input(
            InputType.Text,
            Modifier
                .fillMaxWidth()
                .padding(AppSpacing.S2)
                .borderRadius(8.px)
                .border(1.px, LineStyle.Solid, AppColors.OutlineVariant)
                .outline(0.px)
                .toAttrs {
                    value(value)
                    onInput { onValueChange(it.value) }
                },
        )
    }
}

@Composable
fun AdminTextArea(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(Modifier.fillMaxWidth().margin(bottom = AppSpacing.S2)) {
        Label(attrs = LabelMdStyle.toModifier().margin(bottom = AppSpacing.S1).toAttrs()) { Text(label) }
        TextArea(
            value,
            Modifier
                .fillMaxWidth()
                .height(100.px)
                .padding(AppSpacing.S2)
                .borderRadius(8.px)
                .border(1.px, LineStyle.Solid, AppColors.OutlineVariant)
                .outline(0.px)
                .toAttrs {
                    onInput { onValueChange(it.value) }
                },
        )
    }
}
