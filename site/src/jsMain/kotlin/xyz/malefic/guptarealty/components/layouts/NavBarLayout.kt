package xyz.malefic.guptarealty.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.mdi.MdiMenu
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.components.Footer
import xyz.malefic.guptarealty.components.Logo
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.styles.HideOnMdStyle
import xyz.malefic.guptarealty.styles.LabelSmStyle
import xyz.malefic.guptarealty.styles.NavBarStyle
import xyz.malefic.guptarealty.styles.NavLinkActiveStyle
import xyz.malefic.guptarealty.styles.NavLinkStyle
import xyz.malefic.guptarealty.styles.ShowOnMdStyle
import xyz.malefic.guptarealty.util.Pages

@Layout
@Composable
fun NavBarLayout(content: @Composable () -> Unit) {
    val ctx = rememberPageContext()
    val currentRoute = ctx.route.path
    var isMenuOpen by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().backgroundColor(AppColors.Background)) {
        Box(
            NavBarStyle.toModifier(),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .maxWidth(AppSpacing.ContainerMax)
                    .height(80.px),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Link("/", Modifier.textDecorationLine(TextDecorationLine.None)) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.gap(16.px)) {
                        Logo(Modifier.size(40.px).borderRadius(50.percent).border(1.px, LineStyle.Solid, AppColors.OutlineVariant))
                        Span(HeadlineSmStyle.toModifier().color(AppColors.Primary).toAttrs()) {
                            Text("Gupta Realty")
                        }
                    }
                }

                Box(Modifier.flexGrow(1))

                Row(
                    ShowOnMdStyle.toModifier().gap(32.px),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Pages.entries.forEach { page ->
                        val isActive = page.isCurrentPage(currentRoute)
                        Link(
                            page.route,
                            if (isActive) NavLinkActiveStyle.toModifier() else NavLinkStyle.toModifier(),
                        ) {
                            Text(page.value)
                        }
                    }
                }

                Button(
                    HideOnMdStyle
                        .toModifier()
                        .backgroundColor(Colors.Transparent)
                        .border(0.px)
                        .color(AppColors.Primary)
                        .cursor(Cursor.Pointer)
                        .toAttrs {
                            onClick { isMenuOpen = !isMenuOpen }
                        },
                ) {
                    MdiMenu()
                }
            }
        }

        if (isMenuOpen) {
            Box(
                Modifier
                    .fillMaxSize()
                    .position(Position.Fixed)
                    .zIndex(1000)
                    .backgroundColor(Color.rgba(0, 0, 0, 0.5f))
                    .onClick { isMenuOpen = false },
            ) {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .width(280.px)
                        .backgroundColor(AppColors.Surface)
                        .padding(24.px)
                        .onClick { it.stopPropagation() },
                ) {
                    Row(Modifier.margin(bottom = 32.px).gap(16.px), verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            "https://lh3.googleusercontent.com/aida-public/AB6AXuAwlPPNXG8b5fBKjijADk9kS1VC7TY6AaHEFkTieltaeV8AHMsBnnTrtlvTug_7JklquicNqorWjcp7mLTNEpoOkmrISu31D5AUSBOIUzUknKWtPT0gN1jtn3iQiqgg8TUD3z4i9Y_87CD8LZhj51-IJPsxoxzq__eHA9ArsqJISlDQj-LXNDKLBEm0brRSVvwKCszgOWicF858poQSCaq0kIydrFvzaF5MmMUTHOf9Cz2vEUQ1PMEx708EZ-vcldaEpdHh0-L4v6E",
                            "Ruchika Gupta",
                            Modifier.size(64.px).borderRadius(50.percent),
                        )
                        Column {
                            Span(HeadlineSmStyle.toModifier().color(AppColors.Primary).toAttrs()) { Text("Ruchika Gupta") }
                            Span(LabelSmStyle.toModifier().color(AppColors.OnSurfaceVariant).toAttrs()) { Text("DRE# 02047333") }
                            Span(LabelSmStyle.toModifier().color(AppColors.Secondary).toAttrs()) { Text("714-767-5752") }
                        }
                    }

                    Pages.entries.forEach { page ->
                        val isActive = page.isCurrentPage(currentRoute)
                        Link(
                            page.route,
                            Modifier
                                .fillMaxWidth()
                                .padding(12.px, 16.px)
                                .borderRadius(50.px)
                                .backgroundColor(if (isActive) AppColors.PrimaryFixed else Colors.Transparent)
                                .color(if (isActive) AppColors.OnPrimaryFixedVariant else AppColors.OnSurfaceVariant)
                                .fontWeight(if (isActive) 700 else 400)
                                .textDecorationLine(TextDecorationLine.None)
                                .onClick { isMenuOpen = false },
                        ) {
                            Text(page.value)
                        }
                    }
                }
            }
        }

        Box(Modifier.fillMaxSize().overflow(Overflow.Auto)) {
            content()
        }

        Footer()
    }
}

fun Pages.isCurrentPage(currentRoute: String): Boolean =
    if (this == Pages.INDEX) currentRoute == "/" || currentRoute == "" else currentRoute == route
