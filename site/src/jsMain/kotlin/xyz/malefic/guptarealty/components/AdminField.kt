package xyz.malefic.guptarealty.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.LabelMdStyle

@Composable
fun AdminField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(Modifier.fillMaxWidth().margin(bottom = AppSpacing.S2)) {
        Label(attrs = LabelMdStyle.toModifier().margin(bottom = AppSpacing.S1).toAttrs()) {
            Text(
                label,
            )
        }
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
