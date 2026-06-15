package xyz.malefic.guptarealty.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.vh
import xyz.malefic.guptarealty.api.getTestimonials
import xyz.malefic.guptarealty.components.Loading
import xyz.malefic.guptarealty.components.Polaroid
import xyz.malefic.guptarealty.model.Testimonial

@Page
@Composable
fun TestimonialsPage() {
    Box(Modifier.fillMaxSize().height(100.vh), contentAlignment = Alignment.Center) {
        var testimonials by remember { mutableStateOf<List<Testimonial>?>(null) }

        LaunchedEffect(Unit) {
            testimonials = getTestimonials()
        }

        Loading(testimonials) {
            this.forEach {
                it.imageSrc?.let { src ->
                    Polaroid(it.author, it.quote, src)
                } ?: Polaroid(it.author, it.quote)
            }
        }
    }
}
