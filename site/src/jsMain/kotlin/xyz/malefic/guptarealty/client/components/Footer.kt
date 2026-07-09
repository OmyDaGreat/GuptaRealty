package xyz.malefic.guptarealty.client.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.borderTop
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridColumn
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.ms.MsBusiness
import com.varabyte.kobweb.silk.components.icons.ms.MsMail
import com.varabyte.kobweb.silk.components.icons.ms.MsPhone
import com.varabyte.kobweb.silk.components.icons.ms.MsVerified
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
import xyz.malefic.guptarealty.client.styles.AppColors
import xyz.malefic.guptarealty.client.styles.AppModifiers
import xyz.malefic.guptarealty.client.styles.AppRadius
import xyz.malefic.guptarealty.client.styles.AppSpacing
import xyz.malefic.guptarealty.client.styles.BodyMdStyle
import xyz.malefic.guptarealty.client.styles.ContainerStyle
import xyz.malefic.guptarealty.client.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.client.styles.LabelMdStyle
import xyz.malefic.guptarealty.client.styles.LabelSmStyle
import xyz.malefic.guptarealty.client.styles.SectionInverseStyle
import xyz.malefic.guptarealty.client.styles.SectionWarmStyle

import xyz.malefic.guptarealty.model.SiteInfo

@Composable
fun Footer(siteInfo: SiteInfo?) =
    Box(
        SectionWarmStyle.toModifier().padding(top = 24.px, bottom = 24.px).borderTop(1.px, LineStyle.Solid, AppColors.OutlineVariant),
        Alignment.Center,
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
                    ) { Text(siteInfo?.siteName ?: "Gupta Realty") }
                    P(
                        BodyMdStyle
                            .toModifier()
                            .color(AppColors.OnSurfaceVariant)
                            .margin(bottom = 24.px)
                            .toAttrs(),
                    ) {
                        Text(siteInfo?.footerDescription ?: "A real estate experience defined by sophistication, expertise, and personalized service.")
                    }
                }
                Column {
                    Span(
                        LabelMdStyle
                            .toModifier()
                            .color(AppColors.Primary)
                            .margin(top = 4.px, bottom = 24.px)
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
                        .borderRadius(AppRadius.Lg)
                        .then(AppModifiers.CardBorder),
                ) {
                    Span(
                        LabelMdStyle
                            .toModifier()
                            .color(AppColors.Primary)
                            .margin(bottom = 24.px)
                            .toAttrs(),
                    ) { Text("GET IN TOUCH") }
                    SimpleGrid(numColumns(1, md = 2), Modifier.gap(24.px)) {
                        ContactInfoItem("Direct", siteInfo?.agentPhone ?: "714-767-5752") { MsPhone(it) }
                        ContactInfoItem("Email", siteInfo?.agentEmail ?: "ruchikagupta@firstteam.com") { MsMail(it) }
                        ContactInfoItem("Brokerage", siteInfo?.brokerageName ?: "First Team Real Estate") { MsBusiness(it) }
                        ContactInfoItem("License", siteInfo?.agentLicense ?: "DRE# 02161384") { MsVerified(it) }
                    }
                }
            }
        }
    }

@Composable
fun CopyrightStrip(siteInfo: SiteInfo?) =
    Box(SectionInverseStyle.toModifier().padding(topBottom = AppSpacing.S3), Alignment.Center) {
        Box(ContainerStyle.toModifier()) {
            P(
                LabelSmStyle
                    .toModifier()
                    .color(AppColors.InverseOnSurface)
                    .toAttrs(),
            ) {
                Text("© 2026 ${siteInfo?.siteName ?: "Gupta Realty"}. ${siteInfo?.agentName ?: "Ruchika Gupta"} | ${siteInfo?.agentLicense ?: "DRE# 02161384"}. ${siteInfo?.brokerageName ?: "First Team Real Estate"}. All rights reserved.")
            }
        }
    }
