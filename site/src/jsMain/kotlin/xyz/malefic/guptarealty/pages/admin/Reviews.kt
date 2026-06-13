package xyz.malefic.guptarealty.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.guptarealty.api.getWebinarReviews
import xyz.malefic.guptarealty.api.postWebinarReviews
import xyz.malefic.guptarealty.components.Loading
import xyz.malefic.guptarealty.components.layouts.AdminLayoutData
import xyz.malefic.guptarealty.components.layouts.AdminLayoutScope
import xyz.malefic.guptarealty.components.layouts.AdminPage
import xyz.malefic.guptarealty.model.WebinarReview
import xyz.malefic.guptarealty.styles.AppColors
import xyz.malefic.guptarealty.styles.AppModifiers
import xyz.malefic.guptarealty.styles.AppSpacing
import xyz.malefic.guptarealty.styles.HeadlineMdStyle
import xyz.malefic.guptarealty.styles.HeadlineSmStyle
import xyz.malefic.guptarealty.styles.LabelMdStyle

@InitRoute
fun initReviewsPage(ctx: InitRouteContext) {
    ctx.data.add(AdminLayoutData(AdminPage.REVIEWS))
}

@Page
@Composable
fun AdminLayoutScope.ReviewsPage() {
    var reviews by remember { mutableStateOf<List<WebinarReview>?>(null) }
    var editingReviewIndex by remember { mutableStateOf<Int?>(null) }
    var isCreating by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        reviews = getWebinarReviews()
    }

    Column(Modifier.fillMaxSize().overflow(Overflow.Auto).padding(AppSpacing.S4)) {
        H2(HeadlineMdStyle.toModifier().margin(bottom = AppSpacing.S4).toAttrs()) {
            Text("Review Management")
        }

        if (editingReviewIndex != null || isCreating) {
            ReviewEditor(
                review = if (isCreating) null else reviews!![editingReviewIndex!!],
                onSave = { newReview ->
                    scope.launch {
                        val newList = reviews!!.toMutableList()
                        if (isCreating) {
                            newList.add(newReview)
                        } else {
                            newList[editingReviewIndex!!] = newReview
                        }
                        postWebinarReviews(newList, token)
                        reviews = newList
                        editingReviewIndex = null
                        isCreating = false
                    }
                },
                onCancel = {
                    editingReviewIndex = null
                    isCreating = false
                },
            )
        } else {
            Button(
                Modifier
                    .backgroundColor(AppColors.Secondary)
                    .color(AppColors.OnSecondary)
                    .padding(AppSpacing.S2, AppSpacing.S4)
                    .borderRadius(50.px)
                    .border(0.px)
                    .cursor(Cursor.Pointer)
                    .margin(bottom = AppSpacing.S4)
                    .toAttrs {
                        onClick { isCreating = true }
                    },
            ) { Text("Add New Review") }

            Loading(reviews) { allReviews ->
                Column(Modifier.fillMaxWidth().gap(AppSpacing.S2)) {
                    allReviews.forEachIndexed { index, review ->
                        Row(
                            AppModifiers.Card.padding(AppSpacing.S3).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                review.reviewerImage,
                                "Reviewer",
                                Modifier
                                    .padding(right = AppSpacing.S2)
                                    .borderRadius(50.px)
                                    .fillMaxWidth()
                                    .maxWidth(64.px),
                            )
                            Column(Modifier.weight(1f)) {
                                H2(HeadlineSmStyle.toModifier().toAttrs()) { Text(review.reviewer) }
                                P(LabelMdStyle.toModifier().color(AppColors.OnSurfaceVariant).toAttrs()) {
                                    Text(review.reviewerDescription)
                                }
                            }
                            Row(Modifier.gap(AppSpacing.S2)) {
                                Button(
                                    Modifier
                                        .backgroundColor(AppColors.Primary)
                                        .color(AppColors.OnPrimary)
                                        .padding(AppSpacing.S1, AppSpacing.S3)
                                        .borderRadius(4.px)
                                        .border(0.px)
                                        .cursor(Cursor.Pointer)
                                        .toAttrs { onClick { editingReviewIndex = index } },
                                ) { Text("Edit") }
                                Button(
                                    Modifier
                                        .backgroundColor(Colors.Transparent)
                                        .color(AppColors.Error)
                                        .padding(AppSpacing.S1, AppSpacing.S3)
                                        .borderRadius(4.px)
                                        .border(1.px, LineStyle.Solid, AppColors.Error)
                                        .cursor(Cursor.Pointer)
                                        .toAttrs {
                                            onClick {
                                                scope.launch {
                                                    val newList = reviews!!.toMutableList()
                                                    newList.removeAt(index)
                                                    postWebinarReviews(newList, token)
                                                    reviews = newList
                                                }
                                            }
                                        },
                                ) { Text("Delete") }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewEditor(
    review: WebinarReview?,
    onSave: (WebinarReview) -> Unit,
    onCancel: () -> Unit,
) {
    var reviewer by remember { mutableStateOf(review?.reviewer ?: "") }
    var description by remember { mutableStateOf(review?.reviewerDescription ?: "") }
    var image by remember { mutableStateOf(review?.reviewerImage ?: "") }
    var quote by remember { mutableStateOf(review?.review ?: "") }

    Column(AppModifiers.Card.padding(AppSpacing.S4).fillMaxWidth()) {
        H2(HeadlineSmStyle.toModifier().margin(bottom = AppSpacing.S3).toAttrs()) {
            Text(if (review == null) "New Review" else "Edit Review")
        }

        AdminField("Reviewer Name", reviewer) { reviewer = it }
        AdminField("Reviewer Description", description) { description = it }
        AdminField("Reviewer Image URL", image) { image = it }
        AdminTextArea("Review Quote", quote) { quote = it }

        Row(Modifier.gap(AppSpacing.S2).margin(top = AppSpacing.S3)) {
            Button(
                Modifier
                    .backgroundColor(AppColors.Primary)
                    .color(AppColors.OnPrimary)
                    .padding(AppSpacing.S2, AppSpacing.S4)
                    .borderRadius(50.px)
                    .border(0.px)
                    .cursor(Cursor.Pointer)
                    .fontWeight(FontWeight.Bold)
                    .toAttrs {
                        onClick {
                            onSave(
                                WebinarReview(
                                    reviewer = reviewer,
                                    reviewerDescription = description,
                                    reviewerImage = image,
                                    review = quote,
                                ),
                            )
                        }
                    },
            ) { Text("Save Review") }

            Button(
                Modifier
                    .backgroundColor(Colors.Transparent)
                    .color(AppColors.OnSurfaceVariant)
                    .padding(AppSpacing.S2, AppSpacing.S4)
                    .borderRadius(50.px)
                    .border(1.px, LineStyle.Solid, AppColors.OutlineVariant)
                    .cursor(Cursor.Pointer)
                    .toAttrs { onClick { onCancel() } },
            ) { Text("Cancel") }
        }
    }
}
