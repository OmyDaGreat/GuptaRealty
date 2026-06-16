package xyz.malefic.guptarealty.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import co.touchlab.kermit.Logger
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.silk.components.icons.mdi.MdiDelete
import com.varabyte.kobweb.silk.components.icons.mdi.MdiPersonAdd
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.api.getTestimonials
import xyz.malefic.guptarealty.api.postTestimonials
import xyz.malefic.guptarealty.components.AdminField
import xyz.malefic.guptarealty.components.AdminFieldNull
import xyz.malefic.guptarealty.components.AdminTextArea
import xyz.malefic.guptarealty.components.Loading
import xyz.malefic.guptarealty.components.layouts.AdminLayoutData
import xyz.malefic.guptarealty.components.layouts.AdminLayoutScope
import xyz.malefic.guptarealty.components.layouts.AdminPage
import xyz.malefic.guptarealty.model.Testimonial
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppRadius
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.ContentCardStyle
import xyz.malefic.guptarealty.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.styles.LabelMdStyle
import xyz.malefic.guptarealty.styles.PrimaryButtonStyle
import xyz.malefic.guptarealty.styles.SecondaryButtonStyle
import xyz.malefic.guptarealty.styles.TertiaryButtonStyle

@InitRoute
fun initTestimonialsPage(ctx: InitRouteContext) {
    ctx.data.add(AdminLayoutData(AdminPage.TESTIMONIALS))
}

@Page
@Composable
fun AdminLayoutScope.TestimonialsPage() {
    val scope = rememberCoroutineScope()
    var testimonials by remember { mutableStateOf<List<Testimonial>?>(null) }
    var message by remember { mutableStateOf<Pair<String, Boolean>?>(null) }

    LaunchedEffect(Unit) {
        testimonials = getTestimonials()
    }

    Column(ContentCardStyle.toModifier().fillMaxWidth().margin(bottom = AppSpacing.S4)) {
        Loading(testimonials) {
            H2(HeadlineSmStyle.toModifier().margin(bottom = AppSpacing.S3).toAttrs()) { Text("Testimonials") }

            if (this.isEmpty()) {
                P(LabelMdStyle.toModifier().toAttrs()) { Text("No testimonials yet.") }
            } else {
                this.forEachIndexed { index, testimonial ->
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(AppSpacing.S2)
                            .border(1.px, LineStyle.Dashed, AppColors.OutlineVariant)
                            .borderRadius(AppRadius.Md)
                            .margin(bottom = AppSpacing.S2),
                    ) {
                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            H2(LabelMdStyle.toModifier().toAttrs()) { Text("Review #${index + 1}") }
                            Box(Modifier.weight(1f))
                            Button(
                                TertiaryButtonStyle
                                    .toModifier()
                                    .padding(AppSpacing.S1)
                                    .toAttrs {
                                        onClick {
                                            testimonials = testimonials!!.toMutableList().apply { removeAt(index) }
                                        }
                                    },
                            ) {
                                MdiDelete(Modifier.margin(right = 4.px))
                                Text("Remove")
                            }
                        }
                        AdminField("Author", testimonial.author) {
                            val newList = testimonials!!.toMutableList()
                            newList[index] = testimonial.copy(author = it)
                            testimonials = newList
                        }
                        AdminTextArea("Testimonial", testimonial.quote) {
                            val newList = testimonials!!.toMutableList()
                            newList[index] = testimonial.copy(quote = it)
                            testimonials = newList
                        }
                        AdminFieldNull("Testimonial Image URL", testimonial.imageSrc) {
                            val newList = testimonials!!.toMutableList()
                            newList[index] = testimonial.copy(imageSrc = it)
                            testimonials = newList
                        }
                    }
                }
            }

            Row(Modifier.gap(AppSpacing.S2).margin(top = AppSpacing.S3)) {
                Button(
                    SecondaryButtonStyle
                        .toModifier()
                        .toAttrs {
                            onClick {
                                testimonials = testimonials!! + Testimonial("Author", "Quote")
                            }
                        },
                ) {
                    MdiPersonAdd(Modifier.margin(right = 4.px))
                    Text("Add Review")
                }

                Button(
                    PrimaryButtonStyle
                        .toModifier()
                        .toAttrs {
                            onClick {
                                scope.launch {
                                    try {
                                        postTestimonials(token, testimonials!!)
                                        message = "Testimonials updated successfully" to true
                                    } catch (e: Exception) {
                                        Logger.e(e) { "Failed to update testimonials" }
                                        message = "Failed to update testimonials" to false
                                    }
                                }
                            }
                        },
                ) {
                    Text("Save Testimonials")
                }
            }
        }
    }
}
