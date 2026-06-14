package xyz.malefic.guptarealty.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.InputStyle
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
            InputStyle
                .toModifier()
                .toAttrs {
                    value(value)
                    onInput { onValueChange(it.value) }
                },
        )
    }
}
