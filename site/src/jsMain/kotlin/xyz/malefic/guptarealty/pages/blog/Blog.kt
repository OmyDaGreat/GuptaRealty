package xyz.malefic.guptarealty.pages.blog

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext

@Page("{id}")
@Composable
fun IndividualBlogPage(ctx: PageContext) {
    val blogId = ctx.route.params.getValue("post")
    // ...
}
