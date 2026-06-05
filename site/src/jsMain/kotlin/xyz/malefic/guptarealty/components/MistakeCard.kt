package xyz.malefic.guptarealty.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppModifiers
import xyz.malefic.guptarealty.styles.BodyMdStyle
import xyz.malefic.guptarealty.styles.HeadlineSmStyle

@Composable
fun MistakeCard(
    title: String,
    description: String,
    icon: @Composable (Modifier) -> Unit,
) {
    Column(
        Modifier
            .backgroundColor(AppColors.Background)
            .padding(32.px)
            .borderRadius(24.px)
            .border(1.px, LineStyle.Solid, AppColors.Secondary)
            .then(AppModifiers.SoftShadow),
    ) {
        icon(
            Modifier
                .size(64.px)
                .backgroundColor(AppColors.PrimaryFixed)
                .borderRadius(16.px)
                .display(DisplayStyle.Flex)
                .justifyContent(JustifyContent.Center)
                .alignItems(AlignItems.Center)
                .margin(bottom = 24.px)
                .fontSize(32.px)
                .color(AppColors.Primary),
        )
        H4(HeadlineSmStyle.toModifier().margin(bottom = 16.px).toAttrs()) { Text(title) }
        P(BodyMdStyle.toModifier().color(AppColors.OnSurfaceVariant).toAttrs()) { Text(description) }
    }
}
