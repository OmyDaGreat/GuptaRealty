package xyz.malefic.guptarealty.model

data class Error(
    val error: String,
)

val String.error
    get() = Error(this)
