package xyz.malefic.guptarealty.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.borderTop
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridColumn
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.BodyMdStyle
import xyz.malefic.guptarealty.styles.ContainerStyle
import xyz.malefic.guptarealty.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.styles.LabelMdStyle
import xyz.malefic.guptarealty.styles.LabelSmStyle
import xyz.malefic.guptarealty.styles.SectionStyle

@Composable
fun Footer() =
    Box(
        SectionStyle
            .toModifier()
            .padding(
                top = 24.px,
                bottom = 24.px,
            ).backgroundColor(AppColors.SurfaceLow)
            .borderTop(1.px, LineStyle.Solid, AppColors.OutlineVariant),
        contentAlignment = Alignment.Center,
    ) {
        Column(ContainerStyle.toModifier()) {
            SimpleGrid(numColumns(1, md = 4), Modifier.gap(AppSpacing.Gutter)) {
                Column {
                    H3(
                        HeadlineSmStyle
                            .toModifier()
                            .color(AppColors.Primary)
                            .margin(bottom = 24.px)
                            .toAttrs(),
                    ) { Text("Gupta Realty") }
                    P(
                        BodyMdStyle
                            .toModifier()
                            .color(AppColors.OnSurfaceVariant)
                            .margin(bottom = 24.px)
                            .toAttrs(),
                    ) {
                        Text("A boutique real estate experience defined by sophistication, expertise, and personalized service.")
                    }
                }
                Column {
                    Span(
                        LabelMdStyle
                            .toModifier()
                            .color(AppColors.Primary)
                            .margin(bottom = 24.px)
                            .toAttrs(),
                    ) { Text("EXPLORE") }
                    Link("#", Modifier.color(AppColors.OnSurfaceVariant).margin(bottom = 12.px)) { Text("Buying Guide") }
                    Link("#", Modifier.color(AppColors.OnSurfaceVariant).margin(bottom = 12.px)) { Text("Selling Strategy") }
                    Link("#", Modifier.color(AppColors.OnSurfaceVariant).margin(bottom = 12.px)) { Text("Neighborhood Tours") }
                }
                Column(
                    Modifier
                        .gridColumn("span 2")
                        .backgroundColor(AppColors.SurfaceHigh)
                        .padding(32.px)
                        .borderRadius(24.px),
                ) {
                    Span(
                        LabelMdStyle
                            .toModifier()
                            .color(AppColors.Primary)
                            .margin(bottom = 24.px)
                            .toAttrs(),
                    ) { Text("GET IN TOUCH") }
                    SimpleGrid(numColumns(1, md = 2), Modifier.gap(24.px)) {
                        ContactInfoItem("phone", "Direct", "714-767-5752")
                        ContactInfoItem("mail", "Email", "ruchikagupta@firstteam.com")
                        ContactInfoItem("business", "Brokerage", "First Team Real Estate")
                        ContactInfoItem("verified", "License", "DRE# 02161384")
                    }
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 24.px,
                    ).margin(top = 64.px)
                    .borderTop(1.px, LineStyle.Solid, AppColors.OutlineVariant),
            ) {
                P(LabelSmStyle.toModifier().color(AppColors.OnSurfaceVariant).toAttrs()) {
                    Text("© 2026 Gupta Realty. Ruchika Gupta | DRE# 02047333. First Team Real Estate. All rights reserved.")
                }
            }
        }
    }
