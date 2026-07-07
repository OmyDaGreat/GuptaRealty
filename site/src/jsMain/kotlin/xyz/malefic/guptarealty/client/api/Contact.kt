package xyz.malefic.guptarealty.client.api

import xyz.malefic.guptarealty.client.util.postApi
import xyz.malefic.guptarealty.model.Contact

suspend fun postContact(
    firstName: String,
    lastName: String,
    email: String,
    phone: String,
    message: String,
) = postApi("contact", Contact(firstName, lastName, email, phone, message))
