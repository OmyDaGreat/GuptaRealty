package xyz.malefic.guptarealty.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
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
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.flex
import org.jetbrains.compose.web.css.outline
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
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

@Page
@Composable
fun WebinarsPage() {
    Column(Modifier.fillMaxSize()) {
        WebinarHeroSection()
        MistakesSection()
        TestimonialsSection()
        FinalCTASection()
    }
}

@Composable
fun WebinarHeroSection() {
    Box(SectionStyle.toModifier().padding(top = 40.px, bottom = AppSpacing.SectionGap), contentAlignment = Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            SimpleGrid(
                numColumns(1, lg = 12),
                Modifier.gap(AppSpacing.Gutter).alignItems(com.varabyte.kobweb.compose.css.AlignItems.Center),
            ) {
                Column(Modifier.gridColumn("span 7")) {
                    Span(
                        attrs =
                            Modifier
                                .backgroundColor(AppColors.Primary)
                                .color(AppColors.OnPrimary)
                                .padding(topBottom = 4.px, leftRight = 16.px)
                                .borderRadius(50.px)
                                .margin(bottom = 24.px)
                                .toAttrs(),
                    ) { Text("LIVE EDUCATIONAL SERIES") }
                    H1(DisplayLgStyle.toModifier().margin(bottom = 24.px).toAttrs()) {
                        Text("Mastering the Market: A Guide for First-Time Buyers")
                    }
                    P(
                        BodyLgStyle
                            .toModifier()
                            .color(AppColors.OnSurfaceVariant)
                            .margin(bottom = 40.px)
                            .toAttrs(),
                    ) {
                        Text(
                            "Join Ruchika Gupta for an exclusive deep dive into the 2024 real estate landscape. Learn how to navigate interest rates and find your dream home with confidence.",
                        )
                    }
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .aspectRatio(
                                16,
                                9,
                            ).borderRadius(24.px)
                            .overflow(Overflow.Hidden)
                            .then(AppModifiers.ElevatedShadow)
                            .position(Position.Relative),
                    ) {
                        Image(
                            "https://lh3.googleusercontent.com/aida-public/AB6AXuCM7sKezxKRo-EioXnRhIC_IiX-GdPFc7eJGkEWj4tKCl60ICSqdCPP35EQti9h7fRB4leoi2omg3ptiVBuVcxg8PALJfH_71PnmmxDcu8NUftKKF6pG4VBCQ8QqHpEELt4bQ7w_z8u_jMhn_0eSRxE-NiVhXHzTDIszoeKs_lIsbASO045cROxdCd64vQm-Mu3dwkTOJR225Mtw63B7WPBbI04mTSwLs792n6BuyBftL04Fl1z_hpwzwJi9uDEKj12VCtKrn8_dlY",
                            "Video Thumbnail",
                            Modifier.fillMaxSize().objectFit(ObjectFit.Cover),
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .backgroundColor(
                                    Color.rgba(0, 0, 0, 0.2f),
                                ).display(
                                    DisplayStyle.Flex,
                                ).justifyContent(
                                    com.varabyte.kobweb.compose.css.JustifyContent.Center,
                                ).alignItems(com.varabyte.kobweb.compose.css.AlignItems.Center)
                                .cursor(Cursor.Pointer),
                        ) {
                            Box(
                                Modifier
                                    .size(
                                        80.px,
                                    ).backgroundColor(
                                        AppColors.Secondary,
                                    ).borderRadius(
                                        50.percent,
                                    ).display(
                                        DisplayStyle.Flex,
                                    ).justifyContent(
                                        com.varabyte.kobweb.compose.css.JustifyContent.Center,
                                    ).alignItems(com.varabyte.kobweb.compose.css.AlignItems.Center)
                                    .then(AppModifiers.SoftShadow),
                            ) {
                                Span(attrs = {
                                    classes("material-symbols-outlined")
                                    style {
                                        property("font-size", "36px")
                                        property("color", "white")
                                    }
                                }) { Text("play_arrow") }
                            }
                        }
                    }
                }
                // Registration Form Card
                Box(Modifier.gridColumn("span 5").padding(left = AppSpacing.S5)) {
                    Column(
                        Modifier
                            .backgroundColor(
                                AppColors.Surface,
                            ).padding(
                                48.px,
                            ).borderRadius(40.px)
                            .border(1.px, LineStyle.Solid, AppColors.Secondary)
                            .then(AppModifiers.SoftShadow),
                    ) {
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

                        RegistrationField("Full Name", "Enter your name")
                        RegistrationField("Email Address", "email@example.com", type = InputType.Email)
                        RegistrationField("Phone Number (optional)", "(555) 000-0000", type = InputType.Tel)

                        Button(
                            attrs =
                                PrimaryButtonStyle
                                    .toModifier()
                                    .fillMaxWidth()
                                    .margin(top = 24.px)
                                    .toAttrs(),
                        ) {
                            Text("Register for the Webinar")
                        }
                        P(
                            LabelSmStyle
                                .toModifier()
                                .color(
                                    AppColors.OnSurfaceVariant,
                                ).margin(top = 16.px)
                                .textAlign(TextAlign.Center)
                                .toAttrs(),
                        ) {
                            Text("Next Session: Wednesday, June 21st at 6:00 PM PST")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RegistrationField(
    label: String,
    placeholder: String,
    type: InputType<String> = InputType.Text,
) {
    Column(Modifier.margin(bottom = 24.px)) {
        Span(LabelMdStyle.toModifier().margin(bottom = 8.px).toAttrs()) { Text(label) }
        Input(type, attrs = {
            placeholder(placeholder)
            style {
                width(100.percent)
                backgroundColor(
                    org.jetbrains.compose.web.css
                        .Color(AppColors.SurfaceContainer.toString()),
                )
                border(
                    1.px,
                    LineStyle.Solid,
                    org.jetbrains.compose.web.css
                        .Color(AppColors.Primary.toString()),
                )
                borderRadius(12.px)
                padding(16.px)
                outline("none")
            }
        })
    }
}

@Composable
fun MistakesSection() {
    Box(SectionStyle.toModifier().backgroundColor(AppColors.SurfaceContainer), contentAlignment = Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            Column(Modifier.fillMaxWidth().textAlign(TextAlign.Center).margin(bottom = 64.px)) {
                H2(HeadlineMdStyle.toModifier().margin(bottom = 16.px).toAttrs()) { Text("Common First Time Home Buyer Mistakes") }
                Box(
                    Modifier
                        .size(width = 96.px, height = 6.px)
                        .backgroundColor(AppColors.Secondary)
                        .styleModifier {
                            property("margin-inline", "auto")
                        }.borderRadius(3.px),
                )
            }
            SimpleGrid(numColumns(1, md = 3), Modifier.gap(AppSpacing.Gutter)) {
                MistakeCard(
                    "account_balance_wallet",
                    "Skipping Pre-Approval",
                    "Don't start the hunt without knowing your budget. Pre-approval gives you leverage and clarity in a competitive market.",
                )
                MistakeCard(
                    "home_work",
                    "Ignoring Extra Costs",
                    "Beyond the mortgage, consider closing costs, inspections, and insurance. We help you map out the full financial picture.",
                )
                MistakeCard(
                    "psychology",
                    "Emotional Over-Investing",
                    "It's easy to fall for a aesthetic and ignore structural red flags. We keep you grounded in data and long-term value.",
                )
            }
        }
    }
}

@Composable
fun MistakeCard(
    icon: String,
    title: String,
    description: String,
) {
    Column(
        Modifier
            .backgroundColor(
                AppColors.Background,
            ).padding(32.px)
            .borderRadius(24.px)
            .border(1.px, LineStyle.Solid, AppColors.Secondary)
            .then(AppModifiers.SoftShadow),
    ) {
        Box(
            Modifier
                .size(
                    64.px,
                ).backgroundColor(
                    AppColors.PrimaryFixed,
                ).borderRadius(
                    16.px,
                ).display(
                    DisplayStyle.Flex,
                ).justifyContent(
                    com.varabyte.kobweb.compose.css.JustifyContent.Center,
                ).alignItems(com.varabyte.kobweb.compose.css.AlignItems.Center)
                .margin(bottom = 24.px),
        ) {
            Span(attrs = {
                classes("material-symbols-outlined")
                style {
                    property("font-size", "32px")
                    property("color", AppColors.Primary.toString())
                }
            }) { Text(icon) }
        }
        H4(attrs = HeadlineSmStyle.toModifier().margin(bottom = 16.px).toAttrs()) { Text(title) }
        P(BodyMdStyle.toModifier().color(AppColors.OnSurfaceVariant).toAttrs()) { Text(description) }
    }
}

@Composable
fun TestimonialsSection() {
    Box(SectionStyle.toModifier().backgroundColor(AppColors.Background), contentAlignment = Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            SimpleGrid(numColumns(1, md = 2), Modifier.gap(64.px).alignItems(com.varabyte.kobweb.compose.css.AlignItems.Center)) {
                Column {
                    H2(DisplayLgStyle.toModifier().margin(bottom = 24.px).toAttrs()) { Text("What our attendees are saying") }
                    P(
                        BodyLgStyle
                            .toModifier()
                            .color(AppColors.OnSurfaceVariant)
                            .margin(bottom = 32.px)
                            .toAttrs(),
                    ) {
                        Text("Join hundreds of successful home buyers who started their journey with one of Ruchika's webinars.")
                    }
                    Row(Modifier.gap(16.px)) {
                        TestimonialNavButton("arrow_back")
                        TestimonialNavButton("arrow_forward")
                    }
                }
                Box(
                    Modifier
                        .backgroundColor(
                            AppColors.SurfaceContainer,
                        ).padding(40.px)
                        .borderRadius(40.px)
                        .border(1.px, LineStyle.Solid, AppColors.Secondary)
                        .then(AppModifiers.SoftShadow),
                ) {
                    Column {
                        Row(Modifier.gap(4.px).margin(bottom = 24.px)) {
                            repeat(5) {
                                Span(attrs = {
                                    classes("material-symbols-outlined")
                                    style { property("color", AppColors.Secondary.toString()) }
                                }) { Text("star") }
                            }
                        }
                        P(
                            attrs =
                                HeadlineSmStyle
                                    .toModifier()
                                    .fontWeight(
                                        FontWeight.Normal,
                                    ).fontStyle(FontStyle.Italic)
                                    .margin(bottom = 32.px)
                                    .toAttrs(),
                        ) {
                            Text(
                                "\"Ruchika's webinar was the turning point for us. She simplified the escrow process and gave us the confidence to put in our first offer. We closed last month!\"",
                            )
                        }
                        Row(Modifier.gap(16.px), verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                "https://lh3.googleusercontent.com/aida-public/AB6AXuDABXqZiK9yZKb-RRKmDrqGBFGreGt177dM_PlZcxT6ZuOHNlEbRD_RmtkCEUBpB3CWLb4JPdkNG4Kajom_60ccRqKUfMfkWndi0ZX_iRCqYTru7xuMJW7nn9gnIgnkEufgFEAqeADrQT1QDahHTkRtzVOTr5FnhUOLtA2pS0IN7G6r56Cb-LZhhvqgSjmcCmegKAdHUMIrcNZsYMteuRNNEC46OZH_gZeFvWYwFE7ne8efX6nBMDm2-LmQ3FAEdGctPB1w0BUuuGw",
                                "Reviewer",
                                Modifier.size(56.px).borderRadius(50.percent).objectFit(ObjectFit.Cover),
                            )
                            Column {
                                Span(LabelMdStyle.toModifier().toAttrs()) { Text("Mark & Sarah Thompson") }
                                Span(
                                    LabelSmStyle.toModifier().color(AppColors.OnSurfaceVariant).toAttrs(),
                                ) { Text("New Homeowners, Irvine") }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TestimonialNavButton(icon: String) {
    Button(
        attrs =
            Modifier
                .size(
                    48.px,
                ).borderRadius(
                    50.percent,
                ).border(1.px, LineStyle.Solid, AppColors.Primary)
                .backgroundColor(Colors.Transparent)
                .cursor(Cursor.Pointer)
                .toAttrs(),
    ) {
        Span(attrs = {
            classes("material-symbols-outlined")
            style { property("color", AppColors.Primary.toString()) }
        }) { Text(icon) }
    }
}

@Composable
fun FinalCTASection() {
    Box(
        SectionStyle
            .toModifier()
            .backgroundColor(
                AppColors.Primary,
            ).color(Colors.White)
            .position(Position.Relative)
            .overflow(Overflow.Hidden),
        contentAlignment = Alignment.Center,
    ) {
        Box(ContainerStyle.toModifier().textAlign(TextAlign.Center)) {
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
                    .backgroundColor(
                        Color.rgba(255, 255, 255, 0.1f),
                    ).backdropFilter(
                        com.varabyte.kobweb.compose.css.functions
                            .blur(12.px),
                    ).padding(8.px)
                    .borderRadius(50.px)
                    .maxWidth(600.px)
                    .styleModifier {
                        property("margin-inline", "auto")
                    }.border(1.px, LineStyle.Solid, Color.rgba(255, 255, 255, 0.2f)),
            ) {
                Input(InputType.Email, attrs = {
                    placeholder("Your best email address")
                    style {
                        flex(1)
                        backgroundColor(
                            org.jetbrains.compose.web.css
                                .Color("transparent"),
                        )
                        border(0.px)
                        color(
                            org.jetbrains.compose.web.css
                                .Color("white"),
                        )
                        paddingLeft(24.px)
                        outline("none")
                    }
                })
                Button(
                    attrs =
                        Modifier
                            .backgroundColor(
                                AppColors.Secondary,
                            ).color(
                                Colors.White,
                            ).padding(topBottom = 12.px, leftRight = 32.px)
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
