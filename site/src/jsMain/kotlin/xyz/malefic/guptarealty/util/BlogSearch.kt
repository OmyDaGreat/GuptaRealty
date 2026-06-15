package xyz.malefic.guptarealty.util

import js.objects.unsafeJso
import xyz.malefic.guptarealty.model.BlogPostResponse
import xyz.malefic.guptarealty.util.js.BlogDocument
import xyz.malefic.guptarealty.util.js.MiniSearch
import xyz.malefic.guptarealty.util.js.MiniSearchOptions
import xyz.malefic.guptarealty.util.js.SearchOptions
import xyz.malefic.guptarealty.util.js.SearchResult

object BlogSearch {
    private var index: MiniSearch? = null

    fun build(posts: List<BlogPostResponse>) {
        val options =
            unsafeJso<MiniSearchOptions> {
                fields = arrayOf("title", "summary", "content", "tags")
                storeFields = arrayOf("title", "summary", "imageUrl", "tags", "date")
                idField = "id"
                extractField = { doc, field ->
                    when (field) {
                        "tags" -> doc.tags.joinToString(" ")
                        else -> doc.asDynamic()[field]
                    }
                }
            }

        val docs =
            posts
                .map { post ->
                    unsafeJso<BlogDocument> {
                        id = post.id.toString()
                        title = post.title
                        summary = post.summary
                        content = post.content
                        tags = post.tags.toTypedArray()
                        imageUrl = post.imageUrl
                        date = post.date.toString()
                    }
                }.toTypedArray()

        index = MiniSearch(options).also { it.addAll(docs) }
    }

    fun search(query: String): List<SearchResult> {
        val mini = index ?: return emptyList()
        val opts =
            unsafeJso<SearchOptions> {
                boost =
                    unsafeJso {
                        title = 3
                        summary = 2
                        tags = 2
                        content = 1
                    }
                fuzzy = 0.2
                prefix = true
            }
        return mini.search(query, opts).toList()
    }
}
