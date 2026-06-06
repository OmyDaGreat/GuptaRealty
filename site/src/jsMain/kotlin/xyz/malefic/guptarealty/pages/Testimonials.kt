package xyz.malefic.guptarealty.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun TestimonialsPage() {
    Box(Modifier.fillMaxSize().height(100.vh), contentAlignment = Alignment.Center) {
        Text("Testimonials Page - Coming Soon")
    }
}
