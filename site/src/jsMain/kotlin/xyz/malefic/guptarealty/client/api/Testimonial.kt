package xyz.malefic.guptarealty.client.api

import xyz.malefic.guptarealty.client.util.getApiList
import xyz.malefic.guptarealty.client.util.postApi
import xyz.malefic.guptarealty.model.Testimonial

suspend fun getTestimonials() = getApiList<Testimonial>("testimonials")

suspend fun postTestimonials(
    token: String,
    reviews: List<Testimonial>,
) = postApi("testimonials", reviews, token)
