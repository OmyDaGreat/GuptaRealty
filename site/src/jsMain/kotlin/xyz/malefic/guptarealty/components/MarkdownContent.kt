package xyz.malefic.guptarealty.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toModifier
import org.intellij.markdown.flavours.gfm.GFMFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement
import xyz.malefic.guptarealty.styles.AppFonts

val MarkdownContentStyle =
    CssStyle {
        base {
            Modifier
                .fontFamily(*AppFonts.BODY_STACK)
                .lineHeight(1.8)
        }

        // Use descendant selectors for standard markdown elements
        cssRule(" h1, h2, h3, h4, h5, h6") {
            Modifier
                .fontFamily(*AppFonts.DISPLAY_STACK)
                .fontWeight(600)
                .margin(top = 1.5.px, bottom = 0.5.px)
        }
    }

@Composable
fun MarkdownContent(
    markdown: String,
    modifier: Modifier = Modifier,
) {
    val html =
        remember(markdown) {
            val flavour = GFMFlavourDescriptor()
            val tree = MarkdownParser(flavour).buildMarkdownTreeFromString(markdown)
            HtmlGenerator(markdown, tree, flavour).generateHtml()
        }

    var element by remember { mutableStateOf<HTMLDivElement?>(null) }

    SideEffect {
        element?.innerHTML = html
    }

    Div(
        MarkdownContentStyle.toModifier().then(modifier).toAttrs {
            ref { el ->
                element = el
                el.innerHTML = html
                onDispose { element = null }
            }
        },
    )
}
