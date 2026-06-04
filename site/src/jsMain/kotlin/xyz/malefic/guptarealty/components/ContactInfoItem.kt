package xyz.malefic.guptarealty.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.BodyMdStyle
import xyz.malefic.guptarealty.styles.LabelMdStyle

@Composable
fun ContactInfoItem(
    icon: String,
    label: String,
    value: String,
) {
    Row(Modifier.gap(12.px), verticalAlignment = Alignment.Top) {
        Span(attrs = {
            classes("material-symbols-outlined")
            style { property("color", AppColors.Secondary.toString()) }
        }) { Text(icon) }
        Column {
            Span(LabelMdStyle.toModifier().toAttrs()) { Text(label) }
            Span(BodyMdStyle.toModifier().color(AppColors.OnSurfaceVariant).toAttrs()) { Text(value) }
        }
    }
}
