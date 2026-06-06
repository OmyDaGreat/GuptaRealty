package xyz.malefic.guptarealty.api

import kotlinx.datetime.TimeZone
import xyz.malefic.guptarealty.model.Webinar
import xyz.malefic.guptarealty.model.WebinarReview
import xyz.malefic.guptarealty.model.WebinarTipsSection
import xyz.malefic.guptarealty.util.api

suspend fun getWebinar() = api<Webinar>("webinar?tz=${TimeZone.currentSystemDefault().id}")

suspend fun getWebinarTips() = api<WebinarTipsSection>("webinar/tips")

suspend fun getWebinarReviews() = api<List<WebinarReview>>("webinar/reviews")
