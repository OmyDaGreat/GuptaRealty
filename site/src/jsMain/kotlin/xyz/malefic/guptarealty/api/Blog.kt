package xyz.malefic.guptarealty.api

import xyz.malefic.guptarealty.model.BlogPostRequest
import xyz.malefic.guptarealty.model.BlogPostResponse
import xyz.malefic.guptarealty.util.deleteApi
import xyz.malefic.guptarealty.util.getApi
import xyz.malefic.guptarealty.util.getApiList
import xyz.malefic.guptarealty.util.postApi
import xyz.malefic.guptarealty.util.putApi
import kotlin.uuid.Uuid

suspend fun getBlog() = getApiList<BlogPostResponse>("blog")

suspend fun getBlog(id: Uuid) = getApi<BlogPostResponse>("blog?id=$id")

suspend fun postBlog(
    post: BlogPostRequest,
    token: String,
) = postApi("blog", post, token)

suspend fun putBlog(
    id: Uuid,
    post: BlogPostRequest,
    token: String,
) = putApi("blog/$id", post, token)

suspend fun deleteBlog(
    id: Uuid,
    token: String,
) = deleteApi("blog/$id", token)
