package ir.kasebvatan.blog.models

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgb

enum class Theme(
    val hex: String, val rgb: CSSColorValue
) {

    Primary(
        hex = "#00A2FF", rgb = rgb(r = 0, g = 162, b = 255)
    ),
    LightGray(
        hex = "#fafafa", rgb = rgb(r = 250, g = 250, b = 250)
    )

}