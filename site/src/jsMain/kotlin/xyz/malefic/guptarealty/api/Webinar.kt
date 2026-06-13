package xyz.malefic.guptarealty.api

import kotlinx.datetime.TimeZone
import xyz.malefic.guptarealty.model.Registration
import xyz.malefic.guptarealty.model.Webinar
import xyz.malefic.guptarealty.model.WebinarReview
import xyz.malefic.guptarealty.model.WebinarTipsSection
import xyz.malefic.guptarealty.util.getApi
import xyz.malefic.guptarealty.util.postApi

suspend fun getWebinar() = getApi<Webinar>("webinar?tz=${TimeZone.currentSystemDefault().id}")

suspend fun postWebinar(
    webinar: Webinar,
    token: String,
) = postApi("webinar", webinar, token)

suspend fun getWebinarTips() = getApi<WebinarTipsSection>("webinar/tips")

suspend fun postWebinarTips(
    tips: WebinarTipsSection,
    token: String,
) = postApi("webinar/tips", tips, token)

suspend fun getWebinarReviews() = getApi<List<WebinarReview>>("webinar/reviews")

suspend fun postWebinarReviews(
    reviews: List<WebinarReview>,
    token: String,
) = postApi("webinar/reviews", reviews, token)

suspend fun postWebinarRegistration(
    firstName: String,
    lastName: String,
    email: String,
    phone: String,
    drip: Boolean = false,
) = postApi("webinar/register?drip=$drip", Registration(firstName, lastName, email, phone))
