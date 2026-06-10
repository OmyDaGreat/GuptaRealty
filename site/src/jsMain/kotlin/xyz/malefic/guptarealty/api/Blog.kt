package xyz.malefic.guptarealty.api

import xyz.malefic.guptarealty.model.BlogPostRequest
import xyz.malefic.guptarealty.model.BlogPostResponse
import xyz.malefic.guptarealty.util.deleteApi
import xyz.malefic.guptarealty.util.getApi
import xyz.malefic.guptarealty.util.postApi
import xyz.malefic.guptarealty.util.putApi
import kotlin.uuid.Uuid

suspend fun getBlog() = getApi<List<BlogPostResponse>>("blog")

suspend fun getBlog(id: Uuid) = getApi<BlogPostResponse>("blog?id=$id")

suspend fun postBlog(post: BlogPostRequest) = postApi("blog", post)

suspend fun putBlog(
    id: Uuid,
    post: BlogPostRequest,
) = putApi("blog/$id", post)

suspend fun deleteBlog(id: Uuid) = deleteApi("blog/$id")
