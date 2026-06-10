@file:JsModule("@toast-ui/editor")
@file:JsNonModule

package xyz.malefic.guptarealty.util

external class Editor(
    options: dynamic,
) {
    fun getMarkdown(): String

    fun setMarkdown(markdown: String)

    fun destroy()
}
