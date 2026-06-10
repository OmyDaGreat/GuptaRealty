package xyz.malefic.guptarealty.api

import kotlinx.datetime.TimeZone
import xyz.malefic.guptarealty.model.Registration
import xyz.malefic.guptarealty.model.Webinar
import xyz.malefic.guptarealty.model.WebinarReview
import xyz.malefic.guptarealty.model.WebinarTipsSection
import xyz.malefic.guptarealty.util.getApi
import xyz.malefic.guptarealty.util.postApi

suspend fun getWebinar() = getApi<Webinar>("webinar?tz=${TimeZone.currentSystemDefault().id}")

suspend fun getWebinarTips() = getApi<WebinarTipsSection>("webinar/tips")

suspend fun getWebinarReviews() = getApi<List<WebinarReview>>("webinar/reviews")

suspend fun postWebinarRegistration(
    firstName: String,
    lastName: String,
    email: String,
    phone: String,
    drip: Boolean = false,
) = postApi("webinar/register?drip=$drip", Registration(firstName, lastName, email, phone))
