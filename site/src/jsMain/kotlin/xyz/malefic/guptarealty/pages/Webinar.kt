package xyz.malefic.guptarealty.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.margin
import com.varabyte.kobweb.compose.css.overflowY
import com.varabyte.kobweb.compose.css.scale
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.aspectRatio
import com.varabyte.kobweb.compose.ui.modifiers.backdropFilter
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridColumn
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.scale
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.transform
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.mdi.MdIcon
import com.varabyte.kobweb.silk.components.icons.mdi.MdiArrowBack
import com.varabyte.kobweb.silk.components.icons.mdi.MdiArrowForward
import com.varabyte.kobweb.silk.components.icons.mdi.MdiPlayArrow
import com.varabyte.kobweb.silk.components.icons.mdi.MdiStar
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.flex
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.outline
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.CheckboxInput
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.api.getWebinar
import xyz.malefic.guptarealty.api.getWebinarReviews
import xyz.malefic.guptarealty.api.getWebinarTips
import xyz.malefic.guptarealty.api.postWebinarRegistration
import xyz.malefic.guptarealty.components.Center
import xyz.malefic.guptarealty.components.Loading
import xyz.malefic.guptarealty.components.MistakeCard
import xyz.malefic.guptarealty.model.Webinar
import xyz.malefic.guptarealty.model.WebinarReview
import xyz.malefic.guptarealty.model.WebinarTipsSection
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppModifiers
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.BodyLgStyle
import xyz.malefic.guptarealty.styles.BodyMdStyle
import xyz.malefic.guptarealty.styles.ContainerStyle
import xyz.malefic.guptarealty.styles.DisplayLgStyle
import xyz.malefic.guptarealty.styles.HeadlineMdStyle
import xyz.malefic.guptarealty.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.styles.LabelMdStyle
import xyz.malefic.guptarealty.styles.LabelSmStyle
import xyz.malefic.guptarealty.styles.PrimaryButtonStyle
import xyz.malefic.guptarealty.styles.SectionStyle
import xyz.malefic.guptarealty.util.toDisplayString
import xyz.malefic.kutint.rgba
import kotlin.time.Duration.Companion.milliseconds

@Page
@Composable
fun WebinarPage() {
    Column(Modifier.fillMaxSize()) {
        val coroutineScope = rememberCoroutineScope()

        WebinarHeroSection(coroutineScope)
        TipsSection()
        TestimonialsSection(coroutineScope)
        FinalCTASection()
    }
}

@Composable
fun WebinarHeroSection(coroutineScope: CoroutineScope) =
    Box(
        SectionStyle.toModifier().padding(top = 40.px, bottom = AppSpacing.SectionGap),
        contentAlignment = Alignment.Center,
    ) {
        Box(ContainerStyle.toModifier()) {
            var webinar by remember { mutableStateOf<Webinar?>(null) }

            LaunchedEffect(Unit) {
                webinar = getWebinar()
            }

            Loading(webinar) { w ->
                SimpleGrid(
                    numColumns(1, lg = 12),
                    Modifier.gap(AppSpacing.Gutter).alignItems(AlignItems.Center),
                ) {
                    Column(Modifier.gridColumn("span 7")) {
                        Span(
                            Modifier
                                .backgroundColor(AppColors.Primary)
                                .color(AppColors.OnPrimary)
                                .padding(topBottom = 4.px, leftRight = 16.px)
                                .borderRadius(50.px)
                                .margin(bottom = 24.px)
                                .toAttrs(),
                        ) { Text(w.header) }
                        H1(DisplayLgStyle.toModifier().margin(bottom = 24.px).toAttrs()) {
                            Text(w.title)
                        }
                        P(
                            BodyLgStyle
                                .toModifier()
                                .color(AppColors.OnSurfaceVariant)
                                .margin(bottom = 40.px)
                                .toAttrs(),
                        ) {
                            Text(w.description)
                        }
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(16, 9)
                                .borderRadius(24.px)
                                .overflow(Overflow.Hidden)
                                .then(AppModifiers.ElevatedShadow)
                                .position(Position.Relative),
                        ) {
                            Image(
                                w.imageUrl,
                                w.title,
                                Modifier.fillMaxSize().objectFit(ObjectFit.Cover),
                            )
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .backgroundColor(rgba(0, 0, 0, 0.2f))
                                    .display(DisplayStyle.Flex)
                                    .justifyContent(JustifyContent.Center)
                                    .alignItems(AlignItems.Center)
                                    .cursor(Cursor.Pointer),
                            ) {
                                Box(
                                    Modifier
                                        .size(80.px)
                                        .backgroundColor(AppColors.Secondary)
                                        .borderRadius(50.percent)
                                        .display(DisplayStyle.Flex)
                                        .then(AppModifiers.SoftShadow),
                                ) {
                                    Center {
                                        MdiPlayArrow(Modifier.scale(200.percent).color(Colors.White))
                                    }
                                }
                            }
                        }
                    }
                    RegistrationSection(coroutineScope, w.date.toDisplayString())
                }
            }
        }
    }

enum class WebinarRegistrationStatus {
    Idle,
    Registering,
    Success,
    Error,
}

private fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return emailRegex.matches(email)
}

private fun isValidPhone(phone: String): Boolean {
    val cleaned = phone.replace(Regex("[^0-9]"), "")
    return cleaned.length == 10 && cleaned.all { it.isDigit() }
}

@Composable
fun RegistrationSection(
    coroutineScope: CoroutineScope,
    sessionDate: String,
) = Box(Modifier.gridColumn("span 5").padding(left = AppSpacing.S5)) {
    Column(
        Modifier
            .backgroundColor(AppColors.Surface)
            .padding(48.px)
            .borderRadius(40.px)
            .border(1.px, LineStyle.Solid, AppColors.Secondary)
            .then(AppModifiers.SoftShadow),
    ) {
        val name = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val phone = remember { mutableStateOf("") }

        var registered by remember { mutableStateOf(WebinarRegistrationStatus.Idle) }
        var drip by remember { mutableStateOf(false) }

        LaunchedEffect(registered) {
            if (registered == WebinarRegistrationStatus.Success || registered == WebinarRegistrationStatus.Error) {
                coroutineScope.launch {
                    delay(2000.milliseconds)
                    registered = WebinarRegistrationStatus.Idle
                }
            }
        }

        H3(
            HeadlineMdStyle
                .toModifier()
                .color(AppColors.Primary)
                .margin(bottom = 8.px)
                .toAttrs(),
        ) { Text("Secure Your Spot") }
        P(
            BodyMdStyle
                .toModifier()
                .color(AppColors.OnSurfaceVariant)
                .margin(bottom = 32.px)
                .toAttrs(),
        ) {
            Text("Register now to receive the webinar link and a free Home Buyer's Toolkit PDF.")
        }

        RegistrationField("Full Name", name, "Enter your name")
        RegistrationField("Email Address", email, "email@example.com", type = InputType.Email)
        RegistrationField("Phone Number", phone, "5550000000", type = InputType.Tel)

        Button(
            PrimaryButtonStyle
                .toModifier()
                .fillMaxWidth()
                .margin(top = 24.px)
                .toAttrs {
                    onClick {
                        coroutineScope.launch {
                            registered = WebinarRegistrationStatus.Registering
                            if (name.value.isBlank() || email.value.isBlank() || phone.value.isBlank()) {
                                registered = WebinarRegistrationStatus.Error
                                return@launch
                            }
                            if (!isValidEmail(email.value)) {
                                registered = WebinarRegistrationStatus.Error
                                return@launch
                            }
                            if (!isValidPhone(phone.value)) {
                                registered = WebinarRegistrationStatus.Error
                                return@launch
                            }
                            val nameSplit = name.value.split(" ")
                            when (nameSplit.size) {
                                1 -> {
                                    postWebinarRegistration(name.value, "", email.value, phone.value)
                                }

                                else -> {
                                    postWebinarRegistration(
                                        nameSplit.dropLast(1).joinToString(" "),
                                        nameSplit.last(),
                                        email.value,
                                        phone.value,
                                    )
                                }
                            }
                            registered = WebinarRegistrationStatus.Success
                        }
                    }
                },
        ) {
            Text(
                when (registered) {
                    WebinarRegistrationStatus.Idle -> "Register"
                    WebinarRegistrationStatus.Registering -> "Registering..."
                    WebinarRegistrationStatus.Success -> "Success!"
                    WebinarRegistrationStatus.Error -> "Please Fill Fields"
                },
            )
        }
        Row(Modifier.margin(top = 12.px), verticalAlignment = Alignment.CenterVertically) {
            CheckboxInput(drip) {
                onInput {
                    drip = it.value
                }
                style {
                    scale(18.0 / 14.0)
                    backgroundColor(AppColors.SurfaceContainer)
                    border(1.px, LineStyle.Solid, AppColors.Primary)
                    borderRadius(12.px)
                    margin(48.px)
                    outline("none")
                }
            }
            P(
                LabelSmStyle
                    .toModifier()
                    .color(AppColors.OnSurfaceVariant)
                    .textAlign(TextAlign.Center)
                    .toAttrs(),
            ) {
                Text("I consent to receive promotional emails from Ruchika Gupta, with or without the use of automation.")
            }
        }
        P(
            LabelSmStyle
                .toModifier()
                .color(AppColors.OnSurfaceVariant)
                .margin(top = 16.px)
                .textAlign(TextAlign.Center)
                .toAttrs(),
        ) {
            Text("Next Session: $sessionDate")
        }
    }
}

@Composable
fun RegistrationField(
    label: String,
    value: MutableState<String>,
    placeholder: String,
    type: InputType<String> = InputType.Text,
) {
    Column(Modifier.margin(bottom = 24.px)) {
        Span(LabelMdStyle.toModifier().margin(bottom = 8.px).toAttrs()) { Text(label) }
        Input(type) {
            placeholder(placeholder)
            value(value.value)
            onInput { value.value = it.value }
            style {
                width(100.percent)
                backgroundColor(AppColors.SurfaceContainer)
                border(1.px, LineStyle.Solid, AppColors.Primary)
                borderRadius(12.px)
                padding(16.px)
                outline("none")
            }
        }
    }
}

@Composable
fun TipsSection() {
    Box(SectionStyle.toModifier().backgroundColor(AppColors.SurfaceContainer), contentAlignment = Alignment.Center) {
        Column(ContainerStyle.toModifier()) {
            var mistakes by remember { mutableStateOf<WebinarTipsSection?>(null) }

            LaunchedEffect(Unit) {
                mistakes = getWebinarTips()
            }

            Loading(mistakes) { m ->
                Column(Modifier.fillMaxWidth().textAlign(TextAlign.Center).margin(bottom = 64.px)) {
                    H2(HeadlineMdStyle.toModifier().margin(bottom = 16.px).toAttrs()) { Text(m.header) }
                    Box(
                        Modifier
                            .size(width = 96.px, height = 6.px)
                            .backgroundColor(AppColors.Secondary)
                            .borderRadius(3.px),
                    )
                }
                SimpleGrid(numColumns(1, md = 3), Modifier.gap(AppSpacing.Gutter)) {
                    m.mistakes.forEach { m ->
                        MistakeCard(m.title, m.description) { MdIcon(m.icon, it) }
                    }
                }
            }
        }
    }
}

@Composable
fun TestimonialsSection(coroutineScope: CoroutineScope) {
    var reviews by remember { mutableStateOf<List<WebinarReview>?>(null) }
    var currentIndex by remember { mutableStateOf(0) }
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        reviews = getWebinarReviews()
    }

    fun navigate(delta: Int) {
        val rs = reviews ?: return
        coroutineScope.launch {
            visible = false
            delay(200.milliseconds)
            currentIndex = (currentIndex + delta + rs.size) % rs.size
            visible = true
        }
    }

    Box(SectionStyle.toModifier().backgroundColor(AppColors.Background), contentAlignment = Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            SimpleGrid(numColumns(1, md = 2), Modifier.gap(64.px).alignItems(AlignItems.Center)) {
                Column {
                    H2(DisplayLgStyle.toModifier().margin(bottom = 24.px).toAttrs()) {
                        Text("What our attendees are saying")
                    }
                    P(
                        BodyLgStyle
                            .toModifier()
                            .color(AppColors.OnSurfaceVariant)
                            .margin(bottom = 32.px)
                            .toAttrs(),
                    ) {
                        Text("Join hundreds of successful home buyers who started their journey with one of Ruchika's webinars.")
                    }
                    reviews?.let { rs ->
                        if (rs.size > 1) {
                            Row(Modifier.gap(16.px)) {
                                TestimonialNavButton(onClick = { navigate(-1) }) { MdiArrowBack(it) }
                                TestimonialNavButton(onClick = { navigate(1) }) { MdiArrowForward(it) }
                            }
                        }
                    }
                }
                Loading(reviews) { rs ->
                    TestimonialCard(rs[currentIndex], visible)
                }
            }
        }
    }
}

@Composable
fun TestimonialCard(
    review: WebinarReview,
    visible: Boolean,
) {
    Box(
        Modifier
            .backgroundColor(AppColors.SurfaceContainer)
            .padding(40.px)
            .borderRadius(40.px)
            .border(1.px, LineStyle.Solid, AppColors.Secondary)
            .then(AppModifiers.SoftShadow)
            .transition {
                property("opacity", "transform")
                duration(0.2.s)
                timingFunction(AnimationTimingFunction.Ease)
            }.opacity(if (visible) 1f else 0f)
            .transform {
                if (visible) translateX(0.px) else translateX(16.px)
            },
    ) {
        Column {
            Row(Modifier.gap(4.px).margin(bottom = 24.px)) {
                repeat(5) { MdiStar(Modifier.color(AppColors.Secondary)) }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .margin(bottom = 32.px)
                    .maxHeight(240.px)
                    .styleModifier {
                        overflowY(Overflow.Auto)
                    },
            ) {
                P(
                    HeadlineSmStyle
                        .toModifier()
                        .fontWeight(FontWeight.Normal)
                        .fontStyle(FontStyle.Italic)
                        .toAttrs(),
                ) {
                    Text("\"${review.review}\"")
                }
            }
            Row(Modifier.gap(16.px), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    review.reviewerImage,
                    review.reviewer,
                    Modifier.size(56.px).borderRadius(50.percent).objectFit(ObjectFit.Cover),
                )
                Column {
                    Span(LabelMdStyle.toModifier().toAttrs()) { Text(review.reviewer) }
                    Span(LabelSmStyle.toModifier().color(AppColors.OnSurfaceVariant).toAttrs()) {
                        Text(review.reviewerDescription)
                    }
                }
            }
        }
    }
}

@Composable
fun TestimonialNavButton(
    onClick: () -> Unit,
    icon: @Composable (Modifier) -> Unit,
) {
    Button(
        Modifier
            .size(48.px)
            .borderRadius(50.percent)
            .border(1.px, LineStyle.Solid, AppColors.Primary)
            .backgroundColor(Colors.Transparent)
            .cursor(Cursor.Pointer)
            .onClick { onClick() }
            .toAttrs(),
    ) {
        Center {
            icon(Modifier.color(AppColors.OnSurfaceVariant))
        }
    }
}

@Composable
fun FinalCTASection() {
    Box(
        SectionStyle
            .toModifier()
            .backgroundColor(AppColors.Primary)
            .color(Colors.White)
            .position(Position.Relative)
            .overflow(Overflow.Hidden),
        contentAlignment = Alignment.Center,
    ) {
        Column(ContainerStyle.toModifier().textAlign(TextAlign.Center)) {
            H2(
                DisplayLgStyle
                    .toModifier()
                    .color(Colors.White)
                    .margin(bottom = 32.px)
                    .toAttrs(),
            ) { Text("Ready to Take the First Step?") }
            P(
                BodyLgStyle
                    .toModifier()
                    .color(Color.rgba(255, 255, 255, 0.9f))
                    .margin(bottom = 48.px)
                    .maxWidth(700.px)
                    .styleModifier {
                        property("margin-inline", "auto")
                    }.toAttrs(),
            ) {
                Text(
                    "Seats are limited to ensure all questions are answered. Join Ruchika for this interactive session and start your journey home.",
                )
            }
            Row(
                Modifier
                    .backgroundColor(rgba(255, 255, 255, 0.1f))
                    .backdropFilter(blur(12.px))
                    .padding(8.px)
                    .borderRadius(50.px)
                    .maxWidth(600.px)
                    .styleModifier {
                        property("margin-inline", "auto")
                    }.border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.2f)),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Input(InputType.Email) {
                    placeholder("Your email address")
                    style {
                        flex(1)
                        backgroundColor(Colors.Transparent)
                        border(0.px)
                        color(Colors.White)
                        paddingLeft(24.px)
                        outline("none")
                        fontSize(16.px)
                    }
                }
                Button(
                    Modifier
                        .backgroundColor(AppColors.Secondary)
                        .color(Colors.White)
                        .padding(topBottom = 12.px, leftRight = 32.px)
                        .borderRadius(50.px)
                        .border(0.px)
                        .cursor(Cursor.Pointer)
                        .toAttrs(),
                ) {
                    Text("Register Me Now")
                }
            }
        }
    }
}
