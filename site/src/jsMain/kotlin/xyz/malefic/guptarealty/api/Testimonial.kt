package xyz.malefic.guptarealty.api

import xyz.malefic.guptarealty.model.Testimonial
import xyz.malefic.guptarealty.util.getApiList
import xyz.malefic.guptarealty.util.postApi

suspend fun getTestimonials() = getApiList<Testimonial>("testimonials")

suspend fun postTestimonials(
    token: String,
    reviews: List<Testimonial>,
) = postApi("testimonials", reviews, token)
