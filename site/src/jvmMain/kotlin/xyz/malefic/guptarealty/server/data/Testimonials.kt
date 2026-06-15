package xyz.malefic.guptarealty.server.data

import xyz.malefic.guptarealty.model.Testimonial
import xyz.malefic.guptarealty.server.util.file

var testimonials by file(
    "testimonials.json",
    listOf(
        Testimonial(
            "Ruchika Gupta",
            "this is content a lot of content the most content no content does content like i do content",
            "/Logo.jpg",
        ),
    ),
)
