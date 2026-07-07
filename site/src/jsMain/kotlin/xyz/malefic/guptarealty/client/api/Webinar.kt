package xyz.malefic.guptarealty.client.api

import kotlinx.datetime.TimeZone
import xyz.malefic.guptarealty.client.util.getApi
import xyz.malefic.guptarealty.client.util.getApiList
import xyz.malefic.guptarealty.client.util.postApi
import xyz.malefic.guptarealty.model.Registration
import xyz.malefic.guptarealty.model.Webinar
import xyz.malefic.guptarealty.model.WebinarReview
import xyz.malefic.guptarealty.model.WebinarTipsSection

suspend fun getWebinar() = getApi<Webinar>("webinar?tz=${TimeZone.currentSystemDefault().id}")

suspend fun postWebinar(
    token: String,
    webinar: Webinar,
) = postApi("webinar", webinar, token)

suspend fun getWebinarTips() = getApi<WebinarTipsSection>("webinar/tips")

suspend fun postWebinarTips(
    token: String,
    tips: WebinarTipsSection,
) = postApi("webinar/tips", tips, token)

suspend fun getWebinarReviews() = getApiList<WebinarReview>("webinar/reviews")

suspend fun postWebinarReviews(
    token: String,
    reviews: List<WebinarReview>,
) = postApi("webinar/reviews", reviews, token)

suspend fun getWebinarRegistrations(
    token: String,
    title: String? = null,
) = getApiList<Registration>("webinar/registrations${title?.let { "?title=$title" } ?: ""}", token)

suspend fun postWebinarRegistration(
    firstName: String,
    lastName: String,
    email: String,
    phone: String,
    drip: Boolean = false,
) = postApi("webinar/register?drip=$drip", Registration(firstName, lastName, email, phone))
