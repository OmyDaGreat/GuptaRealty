package xyz.malefic.guptarealty.api

import xyz.malefic.guptarealty.model.BlogPostRequest
import xyz.malefic.guptarealty.model.BlogPostResponse
import xyz.malefic.guptarealty.util.deleteApi
import xyz.malefic.guptarealty.util.getApi
import xyz.malefic.guptarealty.util.postApi
import xyz.malefic.guptarealty.util.putApi
import kotlin.uuid.Uuid

suspend fun getBlogPosts(id: Uuid? = null) = getApi<List<BlogPostResponse>>("blog${id?.let { "?id=$it" } ?: ""}")

suspend fun postBlogPost(post: BlogPostRequest) = postApi("blog", post)

suspend fun putBlogPost(
    id: Uuid,
    post: BlogPostRequest,
) = putApi("blog/$id", post)

suspend fun deleteBlogPost(id: Uuid) = deleteApi("blog/$id")
